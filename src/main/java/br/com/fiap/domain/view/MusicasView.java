package br.com.fiap.domain.view;

import br.com.fiap.domain.entity.Artista;
import br.com.fiap.domain.entity.Estilo;
import br.com.fiap.domain.entity.Musica;
import br.com.fiap.domain.repository.ArtistaRepository;
import br.com.fiap.domain.repository.EstiloRepository;
import br.com.fiap.domain.service.MusicaService;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class MusicasView {

    List<Artista> artistas = new ArrayList<>();
    List<Estilo> estilos = new ArrayList<>();

    private MusicaService service = new MusicaService();

    public void programa() {

        int opcao = 0;

        do {
            opcao = Integer.parseInt( JOptionPane.showInputDialog("""
                            Escolha uma opção:
                            
                            1. Procurar musica pelo nome
                            2. Procurar musica pelo id
                            3. Adiconar uma musica
                            4. Encerrar programa
                            """));

            switch (opcao) {
                case 1 -> {
                    String nome = JOptionPane.showInputDialog( "Insira o nome da musica: " );
                    List<Musica> list = service.findByName( nome );
                    list.forEach( musica -> {
                        JOptionPane.showMessageDialog( null, musica );
                    } );

                    programa();

                }

                case 2 -> {
                    Long id = Long.valueOf(JOptionPane.showInputDialog( "Insira o id da musica: " ));
                    List<Musica> list = Collections.singletonList(service.findById(id));
                    list.forEach( musica -> {
                        JOptionPane.showMessageDialog( null, musica );
                    } );
                    programa();
                }

                case 3 -> {
                    Musica musica = addMusica();
                    JOptionPane.showMessageDialog( null, musica );
                    programa();
                }
            }


        } while (opcao > 0 && opcao < 4);
        System.out.println("Programa encerrado");
        JOptionPane.showMessageDialog(null, "Programa Encerrado");
    }

    public Musica addMusica() {

        Musica m = null;

        ArtistaRepository artistaRepository = new ArtistaRepository();
        EstiloRepository estiloRepository = new EstiloRepository();

        artistas = artistaRepository.findAll();
        estilos = estiloRepository.findAll();

        if (artistas.size() > 0 && estilos.size() > 0) {

            Artista artista = (Artista) JOptionPane.showInputDialog( null, "Artista:", "Artistas disponíveis", JOptionPane.QUESTION_MESSAGE, null, artistas.toArray(), artistas.get( 0 ) );

            if (Objects.isNull(artista)) return m;

            Estilo estilo = (Estilo) JOptionPane.showInputDialog( null, "Estilo", "Genêro da música", JOptionPane.QUESTION_MESSAGE, null, estilos.toArray(), estilos.get( 0 ) );
            if (Objects.isNull(estilo)) return m;

            String musica = "";

            do {
                musica = JOptionPane.showInputDialog( "Informe o nome da música para adicioná-la" );
            } while (musica == "");


            m = new Musica();
            m.addArtista( artista ).setEstilo(estilo).setNome(musica);
            Musica persist = service.persist(m);

            if (Objects.nonNull( persist )) {
                JOptionPane.showMessageDialog( null, "Sua música foi adicionada com sucerro!" );
            } else {
                JOptionPane.showMessageDialog( null, "Não foi possivel adicionar sua musica! " );
            }
        }
        return m;
    }

}