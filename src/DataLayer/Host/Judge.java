package DataLayer.Host;

import DataLayer.Dive;
import DataLayer.Host.Competition;
import DataLayer.Rating;
import DataLayer.Swimmer;

public class Judge {

    private final int JUDGE_ID;

    private String JUDGE_NAME;

    private Competition comp;

    public Judge(int judgeId, String judgeName){
        this.JUDGE_ID = judgeId;
        this.JUDGE_NAME = judgeName;
    }

    public int getJudgeId() {
        return JUDGE_ID;
    }

    public String getJudgeName() {
        return JUDGE_NAME;
    }

    public void submitRating(Swimmer swimmer, Dive dive){
        comp.ratings.add(new Rating(this,swimmer,dive));
    }


}
