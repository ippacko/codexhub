package com.codexhub.inventory.copy;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
public interface CopyRepository extends JpaRepository<Copy, UUID> {}
