(ns clojure-poc.exercise-30
  (:require [clojure.test :refer :all]))


;;Definir la función slice que reciba una cadena cad y un número n y devuelva una lista con todas
;; las subcadenas contiguas de cad cuyo tamaño sea n. Por ejemplo: (slice "abcde" 3) → ("abc" "bcd" "cde")
(defn as-string [partition]
  (apply str partition)
  )
(defn slice [cad n]
  (map as-string (partition n 1 cad))
  )

(deftest testing-function
  (testing "cuando aplicamos la funcion slice, el resultado debería ser el esperado"
    (is (= (slice "abcde" 2) '("ab" "bc" "cd" "de")))
    )

  (testing "cuando aplicamos la funcion slice, el resultado debería ser el esperado"
    (is (= (slice "abcde" 3) '("abc" "bcd" "cde")))
    )

  (testing "probando función apply"
    (is (= (apply str '(a b c)) "abc"))
    )

  (testing "probando función map"
    (is (= (map str '(a b c)) '("a" "b" "c")))
    )

  (testing "probando función partition"
    (is (= (partition 2 1 "abc") '((\a\b) (\b\c)))
        )
    )
  )