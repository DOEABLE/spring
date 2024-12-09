package com.hana4.demo1.entity;

import java.util.List;

import com.hana4.demo1.entity.BaseEntity;
import com.hana4.demo1.entity.CodeInfo;
import com.hana4.demo1.entity.SubCode;
import com.hana4.demo1.entity.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = {
        @UniqueConstraint(
                name = "uniq_Code_codename_info",
                columnNames = {"codename"}
        )})
public class Code extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 32)
    private String codeName;

    @OneToOne(mappedBy = "code", cascade = CascadeType.ALL)
    private CodeInfo codeInfo;

    @OneToMany(mappedBy = "code", fetch = FetchType.EAGER)
    private List<SubCode> subcodes;

    @ManyToMany
    @JoinTable(name = "CodeUser",
            joinColumns = @JoinColumn(name = "code"),
            inverseJoinColumns = @JoinColumn(name = "user"))
    private List<User> codeUsers;
}