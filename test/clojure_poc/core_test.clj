(ns clojure-poc.core-test
  (:require [clojure.test :refer :all]
            [clojure-poc.core :refer :all]))


;; Definir una función para eliminar las ocurrencias de un dato escalar en una lista (a todo nivel).
(defn remove [x l]
  (cond
    (empty? l) l
    (coll? (first l))
    (cons
      (remove x (first l))
      (remove x (rest l))
      )
    (= x (first l))
    (remove x (rest l))
    :else (cons
            (first l)
            (remove x (rest l))
            )
    )
  )

;; Definir la función tercer-angulo que reciba los valores de dos de los ángulos interiores de un triángulo
;; y devuelva el valor del restante.
(defn third-angle [x1 x2]
  (- 180 x1 x2)
  )

;; Definir la función segundos que reciba los cuatro valores (días, horas, minutos y segundos) del tiempo que
;; dura un evento y devuelva el valor de ese tiempo expresado solamente en segundos.
(defn as-seconds [d h m s]
  (if (and (>= d 0) (>= h 0) (>= m 0) (>= s 0))
    (+ (* 24 60 60 d) (* 60 60 h) (* 60 m) s)
    (print (str "Horario invalido")))
  )

(deftest testing-functions
  (testing "when removes a duplicated number from a collection, then all of those must be removed"
           (is (= (remove 1 '(2 1 1 3)) '(2 3)))
           )
  (testing "when try to remove a number from an empty collection, then no one item must be removed "
           (is (= (remove 1 '()) '()))
           )
  (testing "when use third-angle function, then the result must be expected"
           (is (= (third-angle 90 20) 70)
               )
           )
  (testing "when use third-angle function with zero angle, then the result must be 180"
           (is (= (third-angle 0 0) 180))
           )
  (testing "when convert 60 seconds to seconds, then the result must be the expected"
           (is (= (as-seconds 0 0 0 60) 60))
           )
  )