package com.plataformaapoio.crianca.service;

import com.plataformaapoio.crianca.model.Crianca;
import com.plataformaapoio.crianca.repository.CriancaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CriancaService {

    private final CriancaRepository criancaRepository;
    private final CriancaRepository instituicaoRepository;

    public CriancaService(CriancaRepository criancaRepository, CriancaRepository instituicaoRepository) {
        this.criancaRepository = criancaRepository;
        this.instituicaoRepository = instituicaoRepository;
    }

    public Crianca salvar(Long instituicaoId, Crianca crianca) {
        Crianca instituicao = instituicaoRepository.findById(instituicaoId)
                .orElseThrow(() -> new RuntimeException("Instituição não encontrada"));
        crianca.setCrianca(instituicao);
        return criancaRepository.save(crianca);
    }

    public List<Crianca> listarTodas() {
        return criancaRepository.findAll();
    }

    public List<Crianca> listarPorInstituicao(Long instituicaoId) {
        return criancaRepository.findByInstituicaoId(instituicaoId);
    }

    public Optional<Crianca> buscarPorId(Long id) {
        return criancaRepository.findById(id);
    }

    public Crianca atualizar(Long id, Crianca criancaAtualizada) {
        return criancaRepository.findById(id).map(crianca -> {
            crianca.setNome(criancaAtualizada.getNome());
            crianca.setIdade(criancaAtualizada.getIdade());
            return criancaRepository.save(crianca);
        }).orElseThrow(() -> new RuntimeException("Criança não encontrada"));
    }

    public void deletar(Long id) {
        criancaRepository.deleteById(id);
    }
}