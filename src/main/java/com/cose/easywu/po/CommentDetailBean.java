package com.cose.easywu.po;

import java.util.Date;
import java.util.List;

public class CommentDetailBean {

    private int id;
    private String uid;
    private String nickName;
    private String userPhoto;
    private String content;
    private Date createTime;
    private List<ReplyDetailBean> replyList;

    public CommentDetailBean() {
    }

    public CommentDetailBean(String uid, String nickName, String userPhoto, String content) {
        this.uid = uid;
        this.nickName = nickName;
        this.userPhoto = userPhoto;
        this.content = content;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = new Date(createTime);
    }

    public List<ReplyDetailBean> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<ReplyDetailBean> replyList) {
        this.replyList = replyList;
    }

    @Override
    public String toString() {
        return "CommentDetailBean{" +
                "id=" + id +
                ", uid='" + uid + '\'' +
                ", nickName='" + nickName + '\'' +
                ", userPhoto='" + userPhoto + '\'' +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", replyList=" + replyList +
                '}';
    }
}
