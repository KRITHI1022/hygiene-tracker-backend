package com.hygienetracker.backend.controller;

import com.hygienetracker.backend.model.Bin;
import com.hygienetracker.backend.service.BinService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bins")
@RequiredArgsConstructor
public class BinController {

    private final BinService binService;

    @GetMapping("/ward/{wardNumber}")
    public ResponseEntity<List<Bin>> getBinsByWard(@PathVariable String wardNumber) {
        return ResponseEntity.ok(binService.getBinsByWard(wardNumber));
    }

    @PostMapping
    public ResponseEntity<Bin> addBin(@RequestBody Bin bin) {
        return ResponseEntity.ok(binService.addBin(bin));
    }
}