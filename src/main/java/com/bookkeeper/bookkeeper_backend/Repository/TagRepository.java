package com.bookkeeper.bookkeeper_backend.Repository;

import com.bookkeeper.bookkeeper_backend.Model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findByName(String name);
}
