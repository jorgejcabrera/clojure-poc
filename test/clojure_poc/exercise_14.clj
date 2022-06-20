(ns clojure-poc.exercise-14
  (:require [clojure.test :refer :all]))

;; La transcripción es el proceso en el que la secuencia de ADN de un gen se copia (transcribe) para hacer una molécula de ARN.
;; La cadena de ARN transcrita se forma reemplazando cada nucleótido del ADN por su complemento de ARN:
;; G → C,
;; C → G,
;; T → A y, por último,
;; A → U.
;; Definir la función adn2arn que reciba una cadena de ADN y la devuelva transcrita en ARN.

(defn adn-2-arn [adn]
  (clojure.string/replace adn #"g|c|t|a" {"g" "c" "c" "g" "t" "a" "a" "u"})
  )


(deftest testing-functions

  (testing "definir lista para sustituir"
    (is (= (adn-2-arn "gcta") "cgau"))
    )

  (testing "aplicando la replace v por b"
    (is (= (clojure.string/replace "river" #"v" "b") "riber"))
    )
  )