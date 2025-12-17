package com.example.demo.service;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;
    @InjectMocks
    private UsuarioService usuarioService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }



    @Test
    void deveRetornarUsuarioQuandoIdExistir() {
        Long id = 1L;
        Usuario usuario = new Usuario("nome", "email"); // adapt if your model requires fields
        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));

        Usuario resultado = usuarioService.buscarUsuarioPorId(id);

        assertNotNull(resultado);
        assertSame(usuario, resultado);
        verify(usuarioRepository, times(1)).findById(id);
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoExistir() {
        Long id = 2L;
        when(usuarioRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> usuarioService.buscarUsuarioPorId(id));
        verify(usuarioRepository, times(1)).findById(id);
    }

    @Test
    void deveSalvarUsuarioComSucesso() {
        Usuario entrada = new Usuario("nome", "email"); // adapt fields as needed
        Usuario salvo = mock(Usuario.class);
        when(usuarioRepository.save(entrada)).thenReturn(salvo);

        Usuario resultado = usuarioService.salvarUsuario(entrada);

        assertNotNull(resultado);
        assertSame(salvo, resultado);
        verify(usuarioRepository, times(1)).save(entrada);
    }

}
