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

(defn chega-em-pausado
  [hospital departamento pessoa]
  (if (cabe-na-fila? hospital departamento)
    (do (Thread/sleep (* (rand) 2000))
        (update hospital departamento conj pessoa))
    (throw (ex-info "Esta fila já está cheia" {:tetando-adicionar pessoa}))))

(defn atende
  [hospital departamento]
  (update hospital departamento pop))