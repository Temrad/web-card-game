package ua.od.game.model;

public class AccountNotificationEntity {
    private Integer notificationId;

    public Integer getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Integer notificationId) {
        this.notificationId = notificationId;
    }

    @Override
    public String toString() {
        return "\nAccountNotificationEntity{" +
                "notificationId=" + notificationId +
                '}';
    }
}
