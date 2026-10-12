
package com.serviq.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.serviq.ServiceRecord;

public interface ServiceRecordRepository
        extends JpaRepository<ServiceRecord, Long> {
}
