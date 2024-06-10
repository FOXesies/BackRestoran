package order.repository.active;

import org.example.order.model.active.CanceledInfo
import org.springframework.data.jpa.repository.JpaRepository

interface CanceledInfoRepository : JpaRepository<CanceledInfo, Long> {
}