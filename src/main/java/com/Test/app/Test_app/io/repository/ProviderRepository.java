package com.Test.app.Test_app.io.repository;

import com.Test.app.Test_app.io.entities.Invoice;
import com.Test.app.Test_app.io.entities.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {
}
