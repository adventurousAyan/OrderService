package com.example.demo.repository;

import com.example.demo.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    /**
     * <p></p>
     * @param id
     * @return
     */
    @Query("FROM Order o where o.id=:id")
    Order findOderInfoById(@Param("id") long id);

}
