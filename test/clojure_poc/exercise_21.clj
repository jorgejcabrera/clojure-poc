(ns clojure-poc.exercise-21
  (:require [clojure.test :refer :all]))

;; Definir una función para obtener la matriz triangular superior (incluyendo la diagonal principal) de una
;; matriz cuadrada que está representada como una lista de listas.

(defn triangulo-sup [x]
  (map drop (range (count x)) x)
  )


;; 1 2 3 4
;; 5 6 7 8
;; 9 10 11 12
;; 13 14 15 16
(defn triangulo-sup-rec [x]
  (if (= (count x) 1)
    x
    (concat (take 1 x) (triangulo-sup-rec (map rest (rest x))))
    )
  )

(deftest testing-functions

  (testing "aplicando la funcion take 1 a un vector"
    (is (= (take 1 '(5 6 7)) '(5)))
    )

  (testing "aplicando la funcion count a una matriz"
    (is (= (count '(
                    (1 2 3 4)
                    (1 2 3 4)
                    (1 2 3 4)
                    (1 2 3 4)
                    )) '4))
    )

  (testing "aplicando la funcion count a un vector"
    (is (= (count '(1 2 3)) '3))
    )

  (testing "aplicando la funcion map rest al resultado de rest x"
    (is (= (map rest (rest '((1 2 3) (2 3 4) (5 6 7)))) '((3 4) (6 7))))
    )

  (testing "aplicando la funcion rest a una matriz"
    (is (= (rest '((1 2 3) (2 3 4) (5 6 7))) '((2 3 4) (5 6 7))))
    )

  (testing "aplicando la funcion map drop a una matriz"
    (is (= (map drop (take 3 (repeat 1)) '((1 2 3) (4 5 6) (6 7 8))) '((2 3) (5 6) (7 8))))
    )

  (testing "obteniendo matriz superior, para instancia 1"
    (is (= (triangulo-sup
             '(
               (1 2 3)
               (1 2 3)
               (1 2 3)
               )
             ) '((1 2 3) (2 3) (3))
           )
        )
    )

  (testing "obteniendo matriz superior, para instancia 2"
    (is (= (triangulo-sup
             '(
               (1 2 3 4)
               (1 2 3 4)
               (1 2 3 4)
               (1 2 3 4)
               )
             ) '((1 2 3 4) (2 3 4) (3 4) (4))
           )
        )
    )

  (testing "obteniendo matriz superior, para instancia 1 de manera recursiva"
    (is (= (triangulo-sup-rec
             '(
               (1 2 3)
               (1 2 3)
               (1 2 3)
               )
             ) '((1 2 3) (2 3) (3))
           )
        )
    )

  (testing "obteniendo matriz superior, para instancia 2 de manera recursiva"
    (is (= (triangulo-sup-rec
             '(
               (1 2 3 4)
               (1 2 3 4)
               (1 2 3 4)
               (1 2 3 4)
               )
             ) '((1 2 3 4) (2 3 4) (3 4) (4))
           )
        )
    )
  (testing "obteniendo matriz superior, para instancia 3 de manera recursiva"
    (is (= (triangulo-sup-rec
             '(
               (1 2)
               (3 4)
               )
             ) '((1 2) (4))
           )
        )
    )
  )