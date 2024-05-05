(ns hospital.logic)

(defn cabe-na-fila?
  [hospital departamento]
  (-> hospital
      (get departamento)
      (count)
      (< 5)))

(defn chega-em
  [hospital departamento pessoa]
  (if (cabe-na-fila? hospital departamento)
    (update hospital departamento conj pessoa)
    (throw (ex-info "Esta fila já está cheia" {:tetando-adicionar pessoa}))))

; Não é uma função pura porque utiliza o "rand", que faz com que ela traga dois resultados diferntes
; para os mesmos parâmetros
(defn chega-em-pausado
  [hospital departamento pessoa]
  (println "Vendo se posso adicionar" pessoa)
  (if (cabe-na-fila? hospital departamento)
    (do (Thread/sleep (* (rand) 2000))
        (println "Pessoa" pessoa "vai ser adicionada")
        (update hospital departamento conj pessoa))
    (throw (ex-info "Esta fila já está cheia" {:tetando-adicionar pessoa}))))

(defn atende
  [hospital departamento]
  (update hospital departamento pop))