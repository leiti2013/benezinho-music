package br.com.fiap.domain.repository;

import br.com.fiap.domain.entity.Musica;

import java.util.ArrayList;
import java.util.List;

public class MusicaRepository implements Repository<Musica, Long> {


    private  List<Musica> musicas;


    public MusicaRepository() {
        musicas = new ArrayList<>();
    }

    @Override
    public List<Musica> findAll() {
        return musicas;
    }

    @Override
    public Musica findById(Long id) {
        for (Musica musica: musicas) {
            if (musica.getId().equals(id)){
                return musica;
            }
        }
        return null;
    }

    @Override
    public List<Musica> findByName(String texto) {
        return musicas.stream().filter(musica -> musica.getNome().equalsIgnoreCase(texto)).toList();
    }

    @Override
    public Musica persist(Musica musica) {
        musica.setId(musicas.size()+1L);
        musicas.add(musica);
        return musica;
    }
}
