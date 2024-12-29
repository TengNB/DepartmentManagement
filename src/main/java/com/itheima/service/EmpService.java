package com.itheima.service;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.LoginInfo;
import com.itheima.pojo.PageResult;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface EmpService {

    /**
     * 分页查询
     */
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    /**
     * 新增员工
     */
    void save(Emp emp) throws Exception;

    /**
     * 批量删除员工
     */
    void delete(List<Integer> ids);

    /**
     * 根据ID查询员工
     */
    Emp getInfo(Integer id);

    /**
     * 修改员工
     */
    void update(Emp emp);

    /**
     * 查询所有的员工数据
     */
    List<Emp> list();

    /**
     * 员工登录
     */
    LoginInfo login(Emp emp);
}
