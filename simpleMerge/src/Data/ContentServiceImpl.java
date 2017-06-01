package Data;

import Model.ModelProvider;
import Model.ViewerModel;
import com.sun.org.glassfish.gmbal.ParameterNames;

import java.util.ArrayList;

/**
 * Created by ParkHaeSung on 2017-05-30.
 */
public class ContentServiceImpl implements ContentService {
    private ArrayList<ComparableBlock> leftContent;
    private ArrayList<ComparableBlock> rightContent;

    @Override
    public void compare() {
        ViewerModel leftViewerModel = (ViewerModel) ModelProvider.getInstance().getModel("leftViewerModel");
        ViewerModel rightViewerModel = (ViewerModel) ModelProvider.getInstance().getModel("rightViewerModel");
        leftContent = leftViewerModel.getContentsBlock();
        rightContent = rightViewerModel.getContentsBlock();


        ArrayList<ComparableBlock>[] contents;

        ArrayList<ComparableString> leftStringSet = new ArrayList<>();
        ArrayList<ComparableString> rightStringSet = new ArrayList<>();

        for (int i = 0; i < leftContent.size(); i++) {
            for (int j = 0; j < leftContent.get(i).getCount(); j++)
                leftStringSet.add(leftContent.get(i).getContents(j));
        }
        for (int i = 0; i < rightContent.size(); i++) {
            for (int j = 0; j < rightContent.get(i).getCount(); j++)
                rightStringSet.add(rightContent.get(i).getContents(j));
        }

        contents = editDistance(leftStringSet, rightStringSet);

        leftContent = contents[0];
        rightContent = contents[1];
        for(int i = 0 ; i<leftContent.size();i++){
            for(int j = 0;j<leftContent.get(i).getContents().size();j++){
                System.out.println("알고리즘 속 왼쪽꺼 , state = "+leftContent.get(i).getContents().get(j).getState());
            }
        }
        leftViewerModel.setContentsBlock(leftContent);
        rightViewerModel.setContentsBlock(rightContent);

    }

    private int findMin(int num1, int num2, int num3) {
        if (num1 < num2) {
            if (num1 < num3)
                return num1;
            else
                return num3;
        } else {
            if (num2 < num3)
                return num2;
            else
                return num3;
        }
    }

    private ComparableString makeCompStr(byte flag, String content) {
        return new ComparableString.Builder()
                .setFlags(flag)
                .setContent(content)
                .build();
    }

    private ArrayList<ComparableString> reverseStringList(ArrayList<ComparableString> strList) {
        ArrayList<ComparableString> temp = new ArrayList<>();

        for (int i = strList.size() - 1; i >= 0; i--)
            temp.add(strList.get(i));

        return temp;
    }

    /**
     * @param leftStrList  An array list of strings which are read in the left file
     * @param rightStrList An array list of strings which are read in the right file
     * @return
     */
    private ArrayList<ComparableBlock>[] editDistance(ArrayList<ComparableString> leftStrList
            , ArrayList<ComparableString> rightStrList) {
        int leftStrListSize = leftStrList.size();
        int rightStrListSize = rightStrList.size();

        int mismatchPenalty = 1;
        int gapPenalty = 1;

        ArrayList<ComparableBlock>[] resultBlocks = new ArrayList[2];

        ArrayList<ComparableBlock> leftBlocks = new ArrayList<>();
        ArrayList<ComparableBlock> rightBlocks = new ArrayList<>();

        ArrayList<ComparableString> leftStrResult = new ArrayList<>();
        ArrayList<ComparableString> rightStrResult = new ArrayList<>();

        // Score matrix 이용하여 Edit Distance 구현
        int[][] scoreMatrix = new int[leftStrListSize + 1][rightStrListSize + 1];

        scoreMatrix[0][0] = 0;
        for (int i = 1; i < leftStrListSize + 1; i++)
            scoreMatrix[i][0] = i;
        for (int j = 1; j < rightStrListSize + 1; j++)
            scoreMatrix[0][j] = j;

        for (int i = 1; i < leftStrListSize + 1; i++) {
            for (int j = 1; j < rightStrListSize + 1; j++) {
                if (leftStrList.get(i).getContentString()
                        .compareTo(rightStrList.get(j).getContentString()) == 0)
                    mismatchPenalty = 0;
                else
                    mismatchPenalty = 1;

                scoreMatrix[i][j] = findMin(scoreMatrix[i - 1][j - 1] + mismatchPenalty
                        , scoreMatrix[i - 1][j] + gapPenalty
                        , scoreMatrix[i][j - 1] + gapPenalty);
            }
        }

        int idx_left = leftStrListSize;
        int idx_right = rightStrListSize;

        // Trace 하면서 가장 최선의 문자열 비교를 수행
        while (idx_left != 0 || idx_right != 0) {
            // ScoreMatrix 위쪽 벽
            if (idx_left == 0) {
                leftStrResult.add(makeCompStr(ComparableString.EMPTY, ""));
                rightStrResult.add(makeCompStr(ComparableString.ADDED
                        , rightStrList.get(idx_right - 1).getContentString()));

                idx_right--;
            }
            // ScoreMatrix 왼쪽 벽
            else if (idx_right == 0) {
                leftStrResult.add(makeCompStr(ComparableString.ADDED
                        , leftStrList.get(idx_left - 1).getContentString()));
                rightStrList.add(makeCompStr(ComparableString.EMPTY, ""));

                idx_left--;
            }
            // ScoreMatrix 벽이 아닐 때
            else {
                int tempScore = findMin(scoreMatrix[idx_left - 1][idx_right - 1]
                        , scoreMatrix[idx_left - 1][idx_right], scoreMatrix[idx_left][idx_right - 1]);

                // ScoreMatrix를 대각선으로 trace
                if (tempScore == scoreMatrix[idx_left - 1][idx_right - 1]) {
                    // 두 String이 같을 경우
                    if (scoreMatrix[idx_left][idx_right] == tempScore) {
                        leftStrResult.add(makeCompStr(ComparableString.EQUAL
                                , leftStrList.get(idx_left - 1).getContentString()));
                        rightStrResult.add(makeCompStr(ComparableString.EQUAL
                                , leftStrList.get(idx_left - 1).getContentString()));
                    }
                    // 두 String이 다를 경우
                    else {
                        leftStrResult.add(makeCompStr(ComparableString.DIFF
                                , leftStrList.get(idx_left - 1).getContentString()));
                        rightStrList.add(makeCompStr(ComparableString.DIFF
                                , rightStrList.get(idx_right - 1).getContentString()));
                    }
                    idx_left--;
                    idx_right--;
                }
                // ScoreMatrix를 위쪽으로 trace
                else if (tempScore == scoreMatrix[idx_left - 1][idx_right]) {
                    leftStrResult.add(makeCompStr(ComparableString.ADDED
                            , leftStrList.get(idx_left - 1).getContentString()));
                    rightStrResult.add(makeCompStr(ComparableString.EMPTY, ""));

                    idx_left--;
                }
                // ScoreMatrix를 왼쪽으로 trace
                else {
                    leftStrResult.add(makeCompStr(ComparableString.EMPTY, ""));
                    rightStrResult.add(makeCompStr(ComparableString.ADDED
                            , rightStrList.get(idx_right - 1).getContentString()));

                    idx_right--;
                }
            }
        }

        // 역순의 문자열들을 원래 순으로 뒤집기
        leftStrResult = reverseStringList(leftStrResult);
        rightStrResult = reverseStringList(rightStrResult);

        // Block 하나에 담길 임시 Array List
        ArrayList<ComparableString> tempLeftList = new ArrayList<>();
        ArrayList<ComparableString> tempRightList = new ArrayList<>();

        // Block 만들기
        for (int idx = 1; idx < leftStrResult.size(); idx++) {
            tempLeftList.add(leftStrResult.get(idx - 1));
            tempRightList.add(rightStrResult.get(idx - 1));

            if (leftStrResult.get(idx).getState() != rightStrResult.get(idx).getState()) {
                leftBlocks.add(new ComparableBlock(leftStrResult.get(idx - 1).getState(), tempLeftList));
                rightBlocks.add(new ComparableBlock(rightStrResult.get(idx - 1).getState(), tempRightList));
                tempLeftList = new ArrayList<>();
                tempRightList = new ArrayList<>();
            }
        }

        tempLeftList.add(leftStrResult.get(leftStrResult.size() - 1));
        tempRightList.add(rightStrResult.get(rightStrResult.size() - 1));

        leftBlocks.add(new ComparableBlock(leftStrResult.get(leftStrResult.size() - 1).getState(), tempLeftList));
        rightBlocks.add(new ComparableBlock(rightStrResult.get(rightStrResult.size() - 1).getState(), tempRightList));


        resultBlocks[0] = leftBlocks;
        resultBlocks[1] = rightBlocks;


        return resultBlocks;
    }
}
