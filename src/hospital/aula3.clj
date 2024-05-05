(ns hospital.aula3
  (:use [clojure pprint])
  (:require [hospital.logic :as h.logic]
            [hospital.model :as h.model]))

; Símbolo "name" fica associado ao valor "Bernardo" em qualquer thread que acessar o símbolo "name"
(def nome "Bernardo")

; O símbolo anterior foi redefinido
(def nome "Henrique")

(let [nome "guilherme"]
  ; coisa 1
  ; coisa 2
  (println nome)
  ; abaixo não está sendo refeito o binding ao símbolo local,
  ; estou criando um novo símbolo local a este bloco e escondendo
  ; o anterior: isso é SHADOWING
  (let [nome "henrique"]
    ; coisa 3
    ; coisa 4
    (println nome))
  (println nome))

; Um "atom" é um símbolo que pode ser alterado, e isso acontecerá de uma vez só
(def hospital-henrique (atom {}))

(defn testa-atomao
  []
  (let [hospital-henrique (atom { :espera h.model/fila-vazia})]
    (pprint hospital-henrique)

    ; O "@" é um syntactic-sugar para o "deref": ambos servem como mode de leitura para o valor do atom
    (pprint (deref hospital-henrique))
    (pprint @hospital-henrique)

    ; assim não se altera o valor do atom, somente o valor do retorno
    (pprint (assoc @hospital-henrique :laboratorio1 h.model/fila-vazia))
    (pprint (deref hospital-henrique))

    ; O swap serve para trocar o valor dentro de um átomo, aplicando uma função. (existem mais formas de se fazer isso)
    ; Todas as funções no Clojure que têm "!" querem dizer que possuem um efeito colateral
    (swap! hospital-henrique assoc :laboratorio1 h.model/fila-vazia)
    (pprint @hospital-henrique)
    (swap! hospital-henrique assoc :laboratorio2 h.model/fila-vazia)
    (pprint @hospital-henrique)

    ; Update tradicional e imutável, com derreferência que não trará efeitos colaterais
    (update @hospital-henrique :laboratorio1 conj "111")

    (swap! hospital-henrique update :laboratorio1 conj "111")
    (pprint @hospital-henrique)))

; O swap nos permite isolar todas as reatribuições que haviam sido feitas com os "defs"

; (testa-atomao)



(defn chega-em-malvado!
  [hospital pessoa]
  (swap! hospital h.logic/chega-em-pausado :espera pessoa)
  (println "\r\nApós inserir" pessoa))

(defn simula-um-dia-em-paralelo
  []
  (pprint hospital)
  (let [hospital (atom (h.model/novo-hospital))]
    (.start (Thread. (fn [] (chega-em-malvado! hospital "111"))))
    (.start (Thread. (fn [] (chega-em-malvado! hospital "222"))))
    (.start (Thread. (fn [] (chega-em-malvado! hospital "333"))))
    (.start (Thread. (fn [] (chega-em-malvado! hospital "444"))))
    (.start (Thread. (fn [] (chega-em-malvado! hospital "555"))))
    (.start (Thread. (fn [] (chega-em-malvado! hospital "600"))))
    (.start (Thread. (fn [] (Thread/sleep 8000)
                       (pprint hospital))))))


; Na execução dessa função, são tentadas várias vezes inserir os valores acima dentro do hospital,
; ms como são diferentes threads rodando, às vezes o átomo pode ser editado por uma threade tem que
; editado novamente por outra
(simula-um-dia-em-paralelo)





















