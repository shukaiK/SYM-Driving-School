package com.cmpt.focusdriving.models.Reviews;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
public interface reviewRepository extends JpaRepository<review,Integer>{
    List<review> findByStars(String stars);
    List<review> findById(int rid);
    List<review> findByDisplay(String display);
}
