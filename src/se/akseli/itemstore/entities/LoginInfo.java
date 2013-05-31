/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.akseli.itemstore.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Swami
 */
@Entity
@Table(name="LOGIN_INFO")
public class LoginInfo implements Serializable {

    @Id
    @Column(name="LOGIN_INFO_ID")
    @GeneratedValue
    private Integer loginInfoId;
    
    @Column(name="LOGIN_NAME")
    private String loginName;
    
    private String password;
    
    @OneToOne
    @JoinColumn(name="CUSTOMER_ID")
    private Customer customer;
    
    public Integer getLoginInfoId() {
        return loginInfoId;
    }

    public void setLoginInfoId(Integer loginInfoId) {
        this.loginInfoId = loginInfoId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (loginInfoId != null ? loginInfoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LoginInfo)) {
            return false;
        }
        LoginInfo other = (LoginInfo) object;
        if ((this.loginInfoId == null && other.loginInfoId != null) || (this.loginInfoId != null && !this.loginInfoId.equals(other.loginInfoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "se.akseli.itemstore.entities.LoginInfo[ id=" + loginInfoId + " ]";
    }
    
}
