package br.com.fiap.domain.repository;

import br.com.fiap.domain.entity.Artista;
import br.com.fiap.domain.entity.Estilo;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArtistaRepository implements Repository<Artista, Long> {

    private static List<Artista> artistas;

    static{
        artistas = new ArrayList<>();

        Artista hatsuneMiku = new Artista(1L, "Hatsune Miku");

        Artista mShadows = new Artista(2L, "M. Shadows");

        Artista enygma = new Artista(3L, "Enygma");

        Artista manoelGomes = new Artista(4L, "Manoel Gomes");

        artistas.addAll(Arrays.asList(hatsuneMiku, mShadows, enygma, manoelGomes));

    }


    @Override
    public List<Artista> findAll() {
        return artistas;
    }

    @Override
    public Artista findById(Long id) {
        for (int i=0; i<artistas.size(); i++) {
            if (artistas.get(i).getId().equals(id)) {
                return artistas.get(i);
            }
        }
        return null;
    }

    @Override
    public List<Artista> findByName(String texto) {
        return artistas.stream().filter( artista -> artista.getNome().equalsIgnoreCase( texto ) ).toList();
    }


    @Override
    public Artista persist(Artista artista) {
        artista.setId(artistas.size()+1L);
        artistas.add(artista);
        return artista;
    }

}
