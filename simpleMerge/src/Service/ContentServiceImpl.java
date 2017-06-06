package Service;


import Data.ComparableBlock;
import Data.ComparableString;
import Model.CenterModel;
import Model.ModelProvider;
import Model.ViewerModel;

import java.util.ArrayList;

/**
 * Created by ParkHaeSung on 2017-05-30.
 */
public class ContentServiceImpl implements ContentService {

    @Override
    public ArrayList<ComparableBlock>[] compare(ArrayList<ComparableBlock> leftContent, ArrayList<ComparableBlock> rightContent) {


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

        contents = runLCSAlgorithm(leftStringSet, rightStringSet);

        leftContent = contents[0];
        rightContent = contents[1];
        for (int i = 0; i < leftContent.size(); i++) {
            for (int j = 0; j < leftContent.get(i).getContents().size(); j++) {
                System.out.println("알고리즘 속 왼쪽꺼 , state = " + leftContent.get(i).getContents().get(j).getState());
            }
        }
        return contents;
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
    private ArrayList<ComparableBlock>[] runLCSAlgorithm(ArrayList<ComparableString> leftStrList
            , ArrayList<ComparableString> rightStrList) {
        int leftStrListSize = leftStrList.size();
        int rightStrListSize = rightStrList.size();

        ArrayList<ComparableBlock>[] resultBlocks = new ArrayList[2];

        ArrayList<ComparableBlock> leftBlocks = new ArrayList<>();
        ArrayList<ComparableBlock> rightBlocks = new ArrayList<>();

        ArrayList<ComparableString> leftStrResult = new ArrayList<>();
        ArrayList<ComparableString> rightStrResult = new ArrayList<>();

        // Score matrix 이용하여 LCS Algorithm 구현
        int[][] scoreMatrix = new int[leftStrListSize + 1][rightStrListSize + 1];

        scoreMatrix[0][0] = 0;
        for (int i = 1; i < leftStrListSize + 1; i++)
            scoreMatrix[i][0] = 0;
        for (int j = 1; j < rightStrListSize + 1; j++)
            scoreMatrix[0][j] = 0;

        for (int i = 1; i < leftStrListSize + 1; i++) {
            for (int j = 1; j < rightStrListSize + 1; j++) {
                if (leftStrList.get(i - 1).getContentString()
                        .compareTo(rightStrList.get(j - 1).getContentString()) == 0)
                    scoreMatrix[i][j] = scoreMatrix[i - 1][j - 1] + 1;
                else if (scoreMatrix[i][j - 1] > scoreMatrix[i - 1][j])
                    scoreMatrix[i][j] = scoreMatrix[i][j - 1];
                else
                    scoreMatrix[i][j] = scoreMatrix[i - 1][j];
            }
        }

        for (int i = 0; i < leftStrListSize + 1; i++) {
            for (int j = 0; j < rightStrListSize + 1; j++)
                System.out.print(scoreMatrix[i][j]);

            System.out.println();
        }

        int idx_left = leftStrListSize;
        int idx_right = rightStrListSize;

        // Trace 하면서 가장 최선의 문자열 비교를 수행
        while (idx_left != 0 || idx_right != 0) {
            // 문자열이 같을 때
            if (idx_left > 0 && idx_right > 0 && (leftStrList.get(idx_left - 1).isSame(rightStrList.get(idx_right - 1)))) {
                leftStrResult.add(makeCompStr(ComparableString.EQUAL
                        , leftStrList.get(idx_left - 1).getContentString()));
                rightStrResult.add(makeCompStr(ComparableString.EQUAL
                        , leftStrList.get(idx_left - 1).getContentString()));

                idx_left--;
                idx_right--;
            }
            // 오른쪽 문자열이 추가되었을 경우
            else if (idx_right > 0 && (idx_left == 0 || scoreMatrix[idx_left][idx_right - 1] > scoreMatrix[idx_left - 1][idx_right])) {
                leftStrResult.add(makeCompStr(ComparableString.EMPTY, ""));
                rightStrResult.add(makeCompStr(ComparableString.ADDED
                        , rightStrList.get(idx_right - 1).getContentString()));

                idx_right--;
            }
            // 왼쪽 문자열이 추가되었을 경우
            else if (idx_left > 0 && (idx_right == 0 || scoreMatrix[idx_left][idx_right - 1] < scoreMatrix[idx_left - 1][idx_right])) {
                leftStrResult.add(makeCompStr(ComparableString.ADDED
                        , leftStrList.get(idx_left - 1).getContentString()));
                rightStrResult.add(makeCompStr(ComparableString.EMPTY, ""));

                idx_left--;
            }
            // 두 문자열이 다를 경우
            else {
                leftStrResult.add(makeCompStr(ComparableString.DIFF
                        , leftStrList.get(idx_left - 1).getContentString()));
                rightStrResult.add(makeCompStr(ComparableString.DIFF
                        , rightStrList.get(idx_right - 1).getContentString()));

                idx_left--;
                idx_right--;
            }
        }

        // 역순의 문자열들을 원래 순으로 뒤집기
        leftStrResult = reverseStringList(leftStrResult);
        rightStrResult = reverseStringList(rightStrResult);

        // Block 하나에 담길 임시 Array List
        ArrayList<ComparableString> tempLeftList = new ArrayList<>();
        ArrayList<ComparableString> tempRightList = new ArrayList<>();

        comparingState nowState;
        comparingState nextState;

        // Block 만들기
        for (int idx = 0; idx < leftStrResult.size() - 1; idx++) {
            tempLeftList.add(leftStrResult.get(idx));
            tempRightList.add(rightStrResult.get(idx));

            nowState = getComparingState(leftStrResult.get(idx), rightStrResult.get(idx));
            nextState = getComparingState(leftStrResult.get(idx + 1), rightStrResult.get(idx + 1));

            if (!nowState.equals(nextState)) {
                leftBlocks.add(new ComparableBlock(leftStrResult.get(idx).getState(), tempLeftList));
                rightBlocks.add(new ComparableBlock(rightStrResult.get(idx).getState(), tempRightList));
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

    enum comparingState {
        NOTCOMPARED, EQUAL, DIFF, LEFTADDED, RIGHTADDED;
    }

    private comparingState getComparingState(ComparableString str1, ComparableString str2) {
        byte strState1 = str1.getState();
        byte strState2 = str2.getState();

        if (strState1 == strState2) {
            if (strState1 == ComparableString.EQUAL)
                return comparingState.EQUAL;
            else
                return comparingState.DIFF;
        } else {
            if (strState1 == ComparableString.ADDED)
                return comparingState.LEFTADDED;
            else
                return comparingState.RIGHTADDED;
        }
    }

    @Override
    public boolean merge(ViewerModel sourceModel, ViewerModel targetModel) {
        CenterModel centerModel = (CenterModel) ModelProvider.getInstance().getModel("centerModel");
        if (sourceModel.getContentsBlock().get(centerModel.getCompareBlockIndex()).isEqualString()) return false;
        if (sourceModel.getContentsBlock().get(centerModel.getCompareBlockIndex()).isEmptyString()) {
            sourceModel.getContentsBlock().remove(centerModel.getCompareBlockIndex());
            targetModel.getContentsBlock().remove(centerModel.getCompareBlockIndex());
            targetModel.setContentsBlock(targetModel.getContentsBlock());
            sourceModel.setContentsBlock(sourceModel.getContentsBlock());
            return true;
        }
        for (int i = 0; i < sourceModel.getContentsBlock().get(centerModel.getCompareBlockIndex()).getContents().size(); i++) {
            ComparableString str = sourceModel.getContentsBlock().get(centerModel.getCompareBlockIndex()).getContents().get(i);
            ComparableString leftContents = targetModel.getContentsBlock().get(centerModel.getCompareBlockIndex()).getContents().get(i);
            leftContents.setContentString(str.getContentString());
            leftContents.setEqual();
            str.setEqual();
        }
        targetModel.getContentsBlock().get(centerModel.getCompareBlockIndex()).setEqual();
        sourceModel.getContentsBlock().get(centerModel.getCompareBlockIndex()).setEqual();
        targetModel.setContentsBlock(targetModel.getContentsBlock());
        sourceModel.setContentsBlock(sourceModel.getContentsBlock());
        return true;
    }

}
