package com.viadee.sonarquest.skillTree.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.viadee.sonarquest.skillTree.repositories.SonarRuleRepository;

@Entity
@Table(name = "User_Skill")
public class UserSkill {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "skill_name")
    private String name;
    
    @Column(name="is_root")
    private boolean isRoot;

//    @ManyToMany(cascade = CascadeType.MERGE)
//    @JoinTable(name = "User_Skill_Previous", joinColumns = @JoinColumn(name = "user_skill_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "previous_user_skill_id", referencedColumnName = "id"))
//    private List<UserSkill> previousUserSkills = new ArrayList<UserSkill>(0);

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "User_Skill_Following", joinColumns = @JoinColumn(name = "user_skill_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "following_user_skill_id", referencedColumnName = "id"))
    private List<UserSkill> followingUserSkills = new ArrayList<UserSkill>(0);

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "User_Skill_TO_Sonar_Rule", joinColumns = @JoinColumn(name = "user_skill_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "sonar_rule_id", referencedColumnName = "id"))
    private List<SonarRule> sonarRules = new ArrayList<SonarRule>(0);

    public UserSkill() {

    }

    public UserSkill(String description, String name, boolean isRoot) {
        super();
        this.description = description;
        this.name = name;
        this.isRoot=isRoot;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public List<UserSkill> getPreviousUserSkills() {
//        return previousUserSkills;
//    }
//
//    public void setPreviousUserSkills(List<UserSkill> previousUserSkills) {
//        this.previousUserSkills = previousUserSkills;
//    }
//
//    public void addPreviousUserSkill(UserSkill userSkill) {
//        this.previousUserSkills.add(userSkill);
//    }
//
//    public void removePreviousUserSkill(UserSkill userSkill) {
//        this.previousUserSkills.remove(userSkill);
//    }

    public List<UserSkill> getFollowingUserSkills() {
        return followingUserSkills;
    }

    public void setFollowingUserSkills(List<UserSkill> followingUserSkills) {
        this.followingUserSkills = followingUserSkills;
    }

    public void addFollowingUserSkill(UserSkill userSkill) {
        this.followingUserSkills.add(userSkill);
    }

    public void removeFollowingUserSkill(UserSkill userSkill) {
        this.followingUserSkills.remove(userSkill);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SonarRule> getSonarRules() {
        return sonarRules;
    }

    public void setSonarRules(List<SonarRule> sonarRules) {
        this.sonarRules = sonarRules;
    }

    public void addSonarRule(SonarRule sonaRule) {
        this.sonarRules.add(sonaRule);
    }

    public void removeSonarRule(SonarRule sonarRule) {
        this.sonarRules.remove(sonarRule);
    }

    
    public boolean isRoot() {
        return isRoot;
    }

    
    public void setRoot(boolean isRoot) {
        this.isRoot = isRoot;
    }

}