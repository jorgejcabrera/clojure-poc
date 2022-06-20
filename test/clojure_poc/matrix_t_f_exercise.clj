(ns clojure-poc.matrix-t-f-exercise
  (:require [clojure.test :refer :all]))

;;Contabilizar la cantidad de T por lista de una matrix
;; y una longitud dadas. Por ejemplo:
;(sublist '(A B C D E F G) 3 2) → (C D)

(defn is_true [x]
  (cond
    (= x "T") 1
    :else 0
    )
  )

(defn count_trues [s]
  (reduce (fn [a b] (+ a b))
          (map is_true s)
          )
  )

(defn trues [l]
  (map count_trues l)
  )

(defn contar-t-en-lista [l]
  (count (filter (fn [x] (= x "T")) l))
  )
(defn contar-t [m]
  (map contar-t-en-lista m)
  )

(deftest testing-function

  (testing "probando filter"
    (is (= (contar-t-en-lista '("T" "T")) '2))
    )
  (testing "probando la funciion drop"
    (is (= (contar-t '(("T" "F" "F") ("T" "T" "F") ("T" "T" "T"))) '(1 2 3)))
    )
  (testing "probando la funciion drop"
    (is (= (trues '(("T" "F" "F") ("T" "T" "F") ("T" "T" "T"))) '(1 2 3)))
    )
  )

a
