/**
 * Author: Jatin Mittal
 * Date: 27-09-2024
 */

package com.Feedy.repository;

import com.Feedy.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepo extends JpaRepository<Address, Long> {

}
