package com.ong.service;

import com.ong.model.Instituicao;
import com.ong.repository.InstituicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstituicaoService {

    @Autowired
    private InstituicaoRepository repository;

    public List<Instituicao> listarTodas() {
        return repository.findAll();
    }

    public Optional<Instituicao> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    public Instituicao salvar(Instituicao instituicao) {
        return repository.save(instituicao);
    }

    public Instituicao atualizar(Integer id, Instituicao instituicaoAtualizada) {
        return repository.findById(id).map(instituicao -> {
            instituicao.setNome(instituicaoAtualizada.getNome());
            instituicao.setEndereco(instituicaoAtualizada.getEndereco());
            instituicao.setContato(instituicaoAtualizada.getContato());
            instituicao.setResponsavel(instituicaoAtualizada.getResponsavel());
            return repository.save(instituicao);
        }).orElseThrow(() -> new RuntimeException("Instituição não encontrada"));
    }

    public void excluir(Integer id) {
        repository.deleteById(id);
    }
}
