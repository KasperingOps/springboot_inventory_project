package com.kasperin.inventory_management.services;

import com.kasperin.inventory_management.domain.Stationary;
import com.kasperin.inventory_management.repository.StationaryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class StationaryServiceImplTest {

    public static final Long ID = 1L;
    public static final Long ID2 = 2L;
    public static final String NAME = "Glue";
    public static final String NAME2 = "Pencil";
    public static final String BARCODE = "123456";
    public static final String BARCODE2 = "789012";
    public static final double PRICE = 0.5;
    public static final double PRICE2 = 0.3;

    @InjectMocks
    StationaryServiceImpl stationaryService;

    @Mock
    StationaryRepository stationaryRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findAll() {
        // given
        List<Stationary> stationaryList = Arrays
                .asList(new Stationary(), new Stationary());

        when(stationaryRepository.findAll())
                .thenReturn(stationaryList);

        // when
        List<Stationary> result = stationaryService.findAll();

        // then
        verify(stationaryRepository).findAll();
        assertEquals(2, result.size());
    }

    @Test
    void findByName() {
        //given
        Stationary stationary = new Stationary();
        stationary.setId(ID);
        stationary.setBarcode(BARCODE);
        stationary.setName(NAME);
        stationary.setPrice(PRICE);

        when(stationaryRepository.findByName(anyString()))
                .thenReturn(Optional.of(stationary));

        //when
        Optional<Stationary> result = stationaryService
                .findByName(NAME);

        //then
        verify(stationaryRepository).findByName(eq(NAME));

        assertEquals(stationary, result.get());
    }

    @Test
    void findById() {
        // given
        Stationary stationary = new Stationary();
        stationary.setId(ID);

        when(stationaryRepository.findById(anyLong())).thenReturn(Optional.of(stationary));

        // when
        Stationary result = stationaryService.findById(ID).get();

        // then
        verify(stationaryRepository).findById(eq(ID));

        assertEquals(ID, result.getId());
    }

    @Test
    void save() {
        //given
        Stationary savedStationary = new Stationary();
        savedStationary.setId(ID);
        savedStationary.setName(NAME);
        savedStationary.setBarcode(BARCODE);
        savedStationary.setPrice(PRICE);

        when(stationaryRepository.save(any()))
                .thenReturn(savedStationary);

        Stationary result = stationaryService.save(savedStationary);

        verify(stationaryRepository).save(any());

        assertEquals(savedStationary, result);
    }

    @Test
    void post() {
        Stationary stationaryOld = new Stationary();  //sending this id so contents can be updated
        stationaryOld.setId(ID); //1l
        stationaryOld.setBarcode(BARCODE);
        stationaryOld.setName(NAME); //Glue
        stationaryOld.setPrice(PRICE);
        stationaryRepository.save(stationaryOld);

        Stationary stationaryNew = new Stationary();  //updating with this content
        stationaryNew.setId(ID2);//2l
        stationaryNew.setBarcode(BARCODE2);
        stationaryNew.setName(NAME2); //Pencil
        stationaryNew.setPrice(PRICE2);
        stationaryRepository.save(stationaryNew);

        stationaryService.post(ID, stationaryNew);

        assertNotNull(stationaryNew);
        assertEquals(stationaryNew.getName(), stationaryOld.getName());
        assertThat(stationaryOld.getName(), equalTo(stationaryNew.getName()));
        //assertThat(stationaryOld.getId(), equalTo(stationaryNew.getId()));
        assertThat(stationaryOld.getBarcode(), equalTo(stationaryNew.getBarcode()));
        assertThat(stationaryOld.getPrice(), equalTo(stationaryNew.getPrice()));



    }

    @Test
    void deleteById() {
    }
}