package com.gv.MFactura.repository;

import com.gv.MFactura.entity.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFacturaRepository extends JpaRepository<Factura, Long>{

}
