(ns clojure-poc.exercise-36
  (:require [clojure.test :refer :all]))

;;Definir la función sublist que devuelva la sublista correspondiente a una lista, una posición inicial
;; y una longitud dadas. Por ejemplo:
;(sublist '(A B C D E F G) 3 2) → (C D)
(defn discard-nth-first [list position]
  (drop (dec position) list)
  )
(defn sublist [list position length]
  (first (partition length (discard-nth-first list position))
         )
  )
(defn sublist_v2 [list position length]
  (take length (discard-nth-first list position))
  )

(defn remove_first_elements [x, l]
  (cond
    (= x 1) l
    :else (remove_first_elements (dec x) (rest l))
    )
  )

(defn sublist_v3 [l, x, c]
  (nth (partition c (remove_first_elements x l)) 0)
  )


(deftest testing-function

  (testing "probando la funciion drop"
    (is (= (drop 2 '("A" "B" "C" "D" "E" "F" "G")) '("C" "D" "E" "F" "G")))
    )

  (testing "cuando obtengo una sublista de tamaño 2, entonces el resultado debería ser el esperado"
    (is (= (sublist '("A" "B" "C" "D" "E" "F" "G") 3 2) '("C" "D")))
    )

  (testing "cuando obtengo una sublista de tamaño 2, entonces el resultado debería ser el esperado"
    (is (= (sublist_v2 '("A" "B" "C" "D" "E" "F" "G") 3 2) '("C" "D")))
    )

  (testing "cuando obtengo una sublista de tamaño 2, entonces el resultado debería ser el esperado"
    (is (= (sublist_v3 '("A" "B" "C" "D" "E" "F" "G") 3 2) '("C" "D")))
    )
  )

