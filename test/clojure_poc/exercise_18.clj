(ns clojure-poc.exercise-18
  (:require [clojure.test :refer :all]))

;Definir una función para eliminar los elementos repetidos de una lista simple.


(defn diff [s1 s2]
  (mapcat
    (fn [[x n]] (repeat n x))
    (apply merge-with - (map frequencies [s1 s2]))))

(defn remove_from_list
  [l elem]
  (filter (fn [x] (= (= x nil) false)) (replace {elem nil} l)))

(defn non-repeated-values [map-of-frequencies]
  (keys (filter (fn [[k v]] (= v 1)) map-of-frequencies))
  )

(defn remove-duplicated
  [l]
  (if (empty? l)
    l
    (non-repeated-values (frequencies l)))
  )

(deftest testing-function

  (testing "la funcion unique debería devolver solo los elementos que no se repiten"
    (is (= (remove-duplicated '(1 1 2)) '(2)))
    )
  (testing "la funcion non-repeated-values debería devolver solo las keys que no estan repetidas"
    (is (= (non-repeated-values '{1 2, 2 1}) '(2)))
    )
  (testing "la función remove debería eliminar todas las ocurrencias de un elemento"
    (is (= (remove_from_list '(1 1 1 2 2 3) '1) '(2 2 3)))
    )
  (testing "la función frequencies debería funcionar bien"
    (is (= (frequencies '(1 2 3 4)) '{1 1, 2 1, 3 1, 4 1}))
    )
  )