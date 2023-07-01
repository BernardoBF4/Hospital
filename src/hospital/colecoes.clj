(ns hospital.colecoes
  (:use [clojure pprint]))

(defn testa-vetor
  []
  (let [espera [111 222]]
    (println "\r\nVetor")
    (println espera)
    (println (conj espera 333))
    (println (pop espera))
    (println espera)))

(testa-vetor)

(defn testa-lista
  []
  (let [espera '(111 222)]
    (println "\r\nLista")
    (println espera)
    (println (conj espera 333))
    (println (pop espera))
    (println espera)))

(testa-lista)

(defn testa-conjunto
  []
  (let [espera #{111 222}]
    (println "\r\nConjunto")
    (println espera)
    (println (conj espera 333))
    (println (conj espera 111))                             ; Não permite adicionar o mesmo elemento duas vezes
    ;(println (pop espera))                                  ; Conjuntos não têm ordem explícita, como um conjunto matemático
    (println espera)))

(testa-conjunto)

(defn testa-fila
  []
  (let [espera (conj clojure.lang.PersistentQueue/EMPTY "111" "222")]
    (println "\r\nFila")
    (println (seq espera))
    (println (seq (conj espera "333")))
    (println (seq (pop espera)))
    (println (peek espera))
    (pprint espera)))

(testa-fila)























