package com.kcv.account.management.dto.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "USER_LOGIN_ACTIVITY")
public class UserLoginActivity  {

	private static final long serialVersionUID = 7665864750642742134L;

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

	@ManyToOne
	@JoinColumn(name = "LOGIN_PROFILE_ID", nullable = true)
	private UserLoginProfileDTO userLoginProfile;

	@Column(name = "SESSION_ID", nullable = false)
	private String sessionId;

	@Column(name = "START_DATETIME", nullable = false)
	private LocalDateTime startDatetime = LocalDateTime.now();

	@Column(name = "END_DATETIME", nullable = true)
	private LocalDateTime endDatetime;

	@Column(name = "USER_NAME", nullable = true)
	private String userName;

    @Column(name = "SESSION_STATUS", nullable = false)
    private String sessionStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserLoginProfileDTO getUserLoginProfile() {
        return userLoginProfile;
    }

    public void setUserLoginProfile(UserLoginProfileDTO userLoginProfile) {
        this.userLoginProfile = userLoginProfile;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public LocalDateTime getStartDatetime() {
        return startDatetime;
    }

    public void setStartDatetime(LocalDateTime startDatetime) {
        this.startDatetime = startDatetime;
    }

    public LocalDateTime getEndDatetime() {
        return endDatetime;
    }

    public void setEndDatetime(LocalDateTime endDatetime) {
        this.endDatetime = endDatetime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSessionStatus() {
        return sessionStatus;
    }

    public void setSessionStatus(String sessionStatus) {
        this.sessionStatus = sessionStatus;
    }
}
