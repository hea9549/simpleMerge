package Data;

import Model.ModelProvider;
import Model.ViewerModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ParkHaeSung on 2017-05-30.
 */
public class ContentServiceImpl implements ContentService {
    private ArrayList<ComparableString> leftContent;
    private ArrayList<ComparableString> rightContent;

    @Override
    public void compare() {
        ViewerModel viewerModel = (ViewerModel) ModelProvider.getInstance().getModel("viewerModel");
        leftContent = viewerModel.getLeftContents();
        rightContent = viewerModel.getRightContents();

        ArrayList<ComparableString>[] contents = new ArrayList[2];
        contents = editDistance();

        leftContent = contents[0];
        rightContent = contents[1];

        viewerModel.setLeftContents(leftContent);
        viewerModel.setRightContents(rightContent);
//        for (; ; ) {
//            ComparableString a = new ComparableString.Builder()
//                    .setFlags(ComparableString.DIFF)
//                    .setContent("abcd")
//                    .setIndex(1)
//                    .build();
//            leftContent.add(a);
//        }
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

    private ComparableString makeCompStr(byte flag, String content, int index) {
        return new ComparableString.Builder()
                .setFlags(flag)
                .setContent(content)
                .setIndex(index)
                .build();
    }

    private ArrayList<ComparableString>[] editDistance() {
        int leftContentSize = leftContent.size();
        int rightContentSize = rightContent.size();
        int mismatchPenalty = 1;
        int gapPenalty = 1;

        ArrayList<ComparableString>[] result = new ArrayList[2];

        int[][] scoreMatrix = new int[leftContentSize + 1][rightContentSize + 1];

        scoreMatrix[0][0] = 0;
        for (int i = 1; i < leftContentSize; i++) {
            scoreMatrix[i][0] = i;
        }
        for (int j = 1; j < rightContentSize; j++) {
            scoreMatrix[0][j] = j;
        }

        for (int i = 1; i < leftContentSize + 1; i++) {
            for (int j = 1; j < rightContentSize + 1; j++) {
                if (leftContent.get(i).getContentString()
                        .compareTo(rightContent.get(j).getContentString()) == 0) {
                    mismatchPenalty = 0;
                } else {
                    mismatchPenalty = 1;
                }
                scoreMatrix[i][j] = findMin(scoreMatrix[i - 1][j - 1] + mismatchPenalty
                        , scoreMatrix[i - 1][j] + gapPenalty
                        , scoreMatrix[i][j - 1] + gapPenalty);

            }
        }

        ArrayList<ComparableString> leftResult = new ArrayList<ComparableString>();
        ArrayList<ComparableString> rightResult = new ArrayList<ComparableString>();

        int idx_i = leftContentSize;
        int idx_j = rightContentSize;

        int leftIndex = 0;
        int rightIndex = 0;

        ComparableString tempStr;
        int tempScore;

        // ComparableString.Build -> new method "makeCompStr(Flag, content, index)"
        while (idx_i != 0 || idx_j != 0) {
            if (idx_i == 0) {
                tempStr = makeCompStr(ComparableString.EMPTY, "", leftIndex);
//                tempStr = new ComparableString.Builder()
//                        .setFlags(ComparableString.EMPTY)
//                        .setContent("")
//                        .setIndex(leftIndex)
//                        .build();
                leftResult.add(tempStr);
                leftIndex++;

                tempStr = makeCompStr(ComparableString.ADDED
                        , rightContent.get(idx_j - 1).getContentString(), rightIndex);
//                tempStr = new ComparableString.Builder()
//                        .setFlags(ComparableString.ADDED)
//                        .setContent(rightContent.get(idx_j - 1).getContentString())
//                        .setIndex(rightIndex)
//                        .build();
                rightResult.add(tempStr);
                rightIndex++;

                idx_j--;
            } else if (idx_j == 0) {
                tempStr = makeCompStr(ComparableString.ADDED
                        , leftContent.get(idx_i - 1).getContentString(), leftIndex);
//                tempStr = new ComparableString.Builder()
//                        .setFlags(ComparableString.ADDED)
//                        .setContent(leftContent.get(idx_i - 1).getContentString())
//                        .setIndex(leftIndex)
//                        .build();
                leftResult.add(tempStr);
                leftIndex++;

                tempStr = makeCompStr(ComparableString.EMPTY, "", rightIndex);
//                tempStr = new ComparableString.Builder()
//                        .setFlags(ComparableString.EMPTY)
//                        .setContent("")
//                        .setIndex(rightIndex)
//                        .build();
                rightResult.add(tempStr);
                rightIndex++;

                idx_i--;
            } else {
                tempScore = findMin(scoreMatrix[idx_i - 1][idx_j - 1]
                        , scoreMatrix[idx_i - 1][idx_j], scoreMatrix[idx_i][idx_j - 1]);

                if (tempScore == scoreMatrix[idx_i - 1][idx_j - 1]) {
                    if (scoreMatrix[idx_i][idx_j] == tempScore) {
                        tempStr = makeCompStr(ComparableString.EQUAL
                                , leftContent.get(idx_i - 1).getContentString(), leftIndex);
//                        tempStr = new ComparableString.Builder()
//                                .setFlags(ComparableString.EQUAL)
//                                .setContent(leftContent.get(idx_i - 1).getContentString())
//                                .setIndex(leftIndex)
//                                .build();
                        leftResult.add(tempStr);
                        leftIndex++;


                        tempStr = makeCompStr(ComparableString.EQUAL
                                , rightContent.get(idx_j - 1).getContentString(), rightIndex);
//                        tempStr = new ComparableString.Builder()
//                                .setFlags(ComparableString.EQUAL)
//                                .setContent(rightContent.get(idx_j - 1).getContentString())
//                                .setIndex(rightIndex)
//                                .build();
                        rightResult.add(tempStr);
                        rightIndex++;
                    } else {
                        tempStr = makeCompStr(ComparableString.DIFF
                                , leftContent.get(idx_i - 1).getContentString(), leftIndex);
//                        tempStr = new ComparableString.Builder()
//                                .setFlags(ComparableString.DIFF)
//                                .setContent(leftContent.get(idx_i - 1).getContentString())
//                                .setIndex(leftIndex)
//                                .build();
                        leftResult.add(tempStr);
                        leftIndex++;

                        tempStr = makeCompStr(ComparableString.DIFF
                                , rightContent.get(idx_j - 1).getContentString(), rightIndex);
//                        tempStr = new ComparableString.Builder()
//                                .setFlags(ComparableString.DIFF)
//                                .setContent(rightContent.get(idx_j - 1).getContentString())
//                                .setIndex(rightIndex)
//                                .build();
                        rightResult.add(tempStr);
                        rightIndex++;
                    }

                    idx_i--;
                    idx_j--;
                } else if (tempScore == scoreMatrix[idx_i - 1][idx_j]) {
                    tempStr = makeCompStr(ComparableString.ADDED
                            , leftContent.get(idx_i - 1).getContentString(), leftIndex);
//                    tempStr = new ComparableString.Builder()
//                            .setFlags(ComparableString.ADDED)
//                            .setContent(leftContent.get(idx_i - 1).getContentString())
//                            .setIndex(leftIndex)
//                            .build();
                    leftResult.add(tempStr);
                    leftIndex++;

                    tempStr = makeCompStr(ComparableString.EMPTY, "", rightIndex);
//                    tempStr = new ComparableString.Builder()
//                            .setFlags(ComparableString.EMPTY)
//                            .setContent("")
//                            .setIndex(rightIndex)
//                            .build();
                    rightResult.add(tempStr);
                    rightIndex++;

                    idx_i--;
                } else {
                    tempStr = makeCompStr(ComparableString.EMPTY, "", leftIndex);
//                    tempStr = new ComparableString.Builder()
//                            .setFlags(ComparableString.EMPTY)
//                            .setContent("")
//                            .setIndex(leftIndex)
//                            .build();
                    leftResult.add(tempStr);
                    leftIndex++;

                    tempStr = makeCompStr(ComparableString.ADDED
                            , rightContent.get(idx_j - 1).getContentString(), rightIndex);
//                    tempStr = new ComparableString.Builder()
//                            .setFlags(ComparableString.ADDED)
//                            .setContent(rightContent.get(idx_j - 1).getContentString())
//                            .setIndex(rightIndex)
//                            .build();
                    rightResult.add(tempStr);
                    rightIndex++;

                    idx_j--;
                }
            }
        }

        result[0] = leftResult;
        result[1] = rightResult;

        return result;
    }
}
