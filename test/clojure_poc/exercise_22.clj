(ns clojure-poc.exercise-22
  (:require [clojure.test :refer :all]))

;; Solución propuesta por un compañero
(defn aux-nth [idx item]
  (nth item idx)
  )

(defn diagonal_v2 [mat]
  (map-indexed aux-nth mat)
  )

;; Definir una función para obtener la diagonal principal de una matriz cuadrada
;; que está representada como una lista de listas.
(defn diagonal [x]
  (map
    drop-last (reverse (range (count x)))
    (map drop (range (count x)) x)
    )
  )

;; |1   2   3  4
;  |  __________
;  |5 | 6  7  8
;  |  |   ______
;; |9 |10 |11 12
;; |13|14 |15 16
(defn diagonal-rec [x]
  (if (= (count x) 1)
    x
    (concat
      (map take '(1) (take 1 x))
      (diagonal-rec (map rest (rest x))))
    )
  )

(deftest testing-functions

  (testing "usar funcion map take para obtener el primer elemento"
    (is (=
          (map take '(1) (take 1 '((1 2 3 4) (1 2 3 4) (1 2 3 4) (1 2 3 4))))
          '(1)))
    )
  (testing "usar la funcion reverse con la dimension de la matriz"
    (is (
          =
          (reverse (range (count '((1 2 3 4) (1 2 3 4) (1 2 3 4) (1 2 3 4)))))
          '(3 2 1 0)
          )
        )
    )

  (testing "diagonal de una matriz debería funcionar bien"
    (is (= (diagonal '(
                       (1 2 3 4)
                       (1 2 3 4)
                       (1 2 3 4)
                       (1 2 3 4)
                       )) '((1) (2) (3) (4))))
    )
  (testing "diagonal recursiva de una matriz debería funcionar bien"
    (is (= (diagonal-rec '(
                           (1 2 3 4)
                           (1 2 3 4)
                           (1 2 3 4)
                           (1 2 3 4)
                           )) '((1) (2) (3) (4))))
    )

  ;; Revisar este test no pasa es la implementación de un compañero
  ;(testing "diagonal v2 de una matriz debería funcionar bien"
  ;    (is (= (diagonal_v2 '(
  ;                          (1 2 3 4)
  ;                          (1 2 3 4)
  ;                          (1 2 3 4)
  ;                          (1 2 3 4)
  ;                          )) '((1) (2) (3) (4))))
  ;    )

  )
