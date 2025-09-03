package com.codexhub.inventory.web;

import com.codexhub.inventory.copy.Copy;
import com.codexhub.inventory.copy.CopyRepository;
import com.codexhub.inventory.reservation.Reservation;
import com.codexhub.inventory.reservation.ReservationRepository;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final CopyRepository copies;
    private final ReservationRepository reservations;

    public InventoryController(CopyRepository copies, ReservationRepository reservations) {
        this.copies = copies;
        this.reservations = reservations;
    }

    record CopyReq(UUID bookId, @NotBlank String status) {}
    record ReserveReq(UUID copyId, @NotBlank String userEmail) {}

    @PostMapping("/copies")
    public Copy createCopy(@RequestBody CopyReq req){
        Copy c = new Copy();
        c.setBookId(req.bookId());
        c.setStatus(req.status());
        return copies.save(c);
    }

    @GetMapping("/copies")
    public Page<Copy> listCopies(Pageable pageable){ return copies.findAll(pageable); }

    @PostMapping("/reservations")
    public Reservation reserve(@RequestBody ReserveReq req){
        Reservation r = new Reservation();
        r.setCopyId(req.copyId());
        r.setUserEmail(req.userEmail());
        return reservations.save(r);
    }

    @GetMapping("/reservations")
    public Page<Reservation> listReservations(Pageable pageable){ return reservations.findAll(pageable); }
}
