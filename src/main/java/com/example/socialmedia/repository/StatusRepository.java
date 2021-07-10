package com.example.socialmedia.repository;

import com.example.socialmedia.model.Status;
import com.example.socialmedia.model.enumeration.PrivacyEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {

    boolean existsStatusById(int id);

    List<Status> findAllByUserIdOrderByUpdatedAtDesc(int userId);

    List<Status> findAllByPrivacyOrderByUpdatedAtDesc(PrivacyEnum privacy);

    List<Status> findAllByPrivacyAndUserIdOrderByUpdatedAtDesc(PrivacyEnum privacy, int userId);

    List<Status> findAllByUserEmail(String email);

}
