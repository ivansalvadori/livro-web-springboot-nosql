package br.edu.utfpr.td.tsi.clinica.medica.dominio;

import java.util.List;

public class ResultadoPaginado<T> {
    private List<T> itens;
    private int paginaAtual;
    private int tamanhoPagina;
    private int totalPaginas;
    private long totalRegistros;
    private boolean temAnterior;
    private boolean temProxima;

    public List<T> getItens() {
        return itens;
    }

    public void setItens(List<T> itens) {
        this.itens = itens;
    }

    public int getPaginaAtual() {
        return paginaAtual;
    }

    public void setPaginaAtual(int paginaAtual) {
        this.paginaAtual = paginaAtual;
    }

    public int getTamanhoPagina() {
        return tamanhoPagina;
    }

    public void setTamanhoPagina(int tamanhoPagina) {
        this.tamanhoPagina = tamanhoPagina;
    }

    public int getTotalPaginas() {
        return totalPaginas;
    }

    public void setTotalPaginas(int totalPaginas) {
        this.totalPaginas = totalPaginas;
    }

    public long getTotalRegistros() {
        return totalRegistros;
    }

    public void setTotalRegistros(long totalRegistros) {
        this.totalRegistros = totalRegistros;
    }

    public boolean isTemAnterior() {
        return temAnterior;
    }

    public void setTemAnterior(boolean temAnterior) {
        this.temAnterior = temAnterior;
    }

    public boolean isTemProxima() {
        return temProxima;
    }

    public void setTemProxima(boolean temProxima) {
        this.temProxima = temProxima;
    }
}