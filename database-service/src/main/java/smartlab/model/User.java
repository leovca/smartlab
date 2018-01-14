package smartlab.model;

public class User {
    private Integer id;
    private String name;
    private String deviceId;
    private UserStatus userStatus;

    public User() {
    }

    public User(Integer id, String name, String deviceId, UserStatus userStatus) {
        this.id = id;
        this.name = name;
        this.deviceId = deviceId;
        this.userStatus = userStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public boolean isON(){
        return this.userStatus.equals(UserStatus.ON);
    }
}
