package github.beginner.noname.domain.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * MapperSuperclass 注解表名继承该类的对象可以映射父类对象到数据库中
 *
 * @author zyp on 2018-12-5.
 */
@Data
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "create_date")
    private Long createDate;

    @Column(name = "update_date")
    private Long updateDate;

    @Column(name = "update_by")
    private Integer updateBy;

    public void onCreate() {
        this.createDate = System.currentTimeMillis();
        this.updateDate = System.currentTimeMillis();
        this.updateBy = 1;
    }

    public void onUpdate(Integer updateBy) {
        this.updateDate = System.currentTimeMillis();
        this.updateBy = updateBy;
    }
}
