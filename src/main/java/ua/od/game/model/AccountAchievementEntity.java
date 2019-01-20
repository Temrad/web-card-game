package ua.od.game.model;

public class AccountAchievementEntity {
    private Integer id;
    private Integer achievementId;
    private Integer accountAchievementId;
    private Float amount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAchievementId() {
        return achievementId;
    }

    public void setAchievementId(Integer achievementId) {
        this.achievementId = achievementId;
    }

    public Integer getAccountAchievementId() {
        return accountAchievementId;
    }

    public void setAccountAchievementId(Integer accountAchievementId) {
        this.accountAchievementId = accountAchievementId;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "AccountAchievementEntity{" +
                "id=" + id +
                ", achievementId=" + achievementId +
                ", accountAchievementId=" + accountAchievementId +
                ", amount=" + amount +
                '}';
    }

}
