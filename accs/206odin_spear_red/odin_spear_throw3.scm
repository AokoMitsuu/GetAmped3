	� G */accs/206odin_spear_red/mdl/odin_spear.kmd��       }RAn�0<���^�%)R:$q�C� @OMQkI$ȍc'���.I9Ң� rg���.?�[]aIŲ��D|�jQ<p#5�ǣ�H�Z k�ȸ`�����pUn��
���%�>�U�@vx��������(Y���u<:�.�N������_��;4��&n�hW�Q�&S?��i%+D`��0�Yr��k$~g`䖴HN>���#G�_����\6
��`E8 �1��JR��O��T. _ӈ|�I��4�{|����X�����_�u�T�M��7;�7��"��d��}紣�[�Ԏ�������4J���4~d�vD�<g��Yr0�P+�k ,LX�J�8+��ɡYޓ季�\�c��F��|2c��Y�|U��Vf�F�A�Q�(���I�WX�o��D+zg�i|���-�r�Op^����6��}d�N��XKa�P�_9�'� RpovH<u���D���&�O�>�+Կ��l��� 	[
>�   ?  Lga2/stage/AmpedScript; e start setBlend
 d h 3
 H 0 3 setColor (FFFF)V
 d k l 	fadeColor (FFFFI)V
 d n o2  -              (FFF)Lkotori/geom/Vector3D;
 s + t 	getAttack ()Lga2/stage/Attack;
 f v w Lga2/stage/Attack; y getAttackContext ()Lga2/stage/atk/AttackContext;
 z { | Lga2/stage/atk/AttackContext; ~ 	getMatrix *(Lkotori/geom/Matrix;)Lkotori/geom/Matrix;
  � � 
transform0 ,(Lkotori/geom/Matrix;)Lkotori/geom/Vector3D;
 s � � Lkotori/geom/KMath; � getDir (Lkotori/geom/Vector3D;)F
 � � � R2D (F)F
 � � � slopeY (F)Lkotori/geom/Vector3D;
 s � � scale
 s � �A�  � d2�D ,/accs/206odin_spear_red/odin_spear_throw.scm�        ?���?�  ?�  ?�      B�      � hit_bg d�  �              ,�>�         Lga2/stage/atk/ShootAttack; � vy	 � � 	 s R  vz	 � � 	 s S  attack E(Lkotori/geom/Vector3D;Lga2/stage/atk/AttackParam;)Lga2/stage/Attack;
 f � �-             kill
 f � 1 f  g 1  �*� iW*� jW*� mW*d� jW*�� pW*�� jWqLrM,� uW*� x� *� x� }� ,*� x� }+� �� �W,� �F,%� �v� �W,�� �W�:,� �v� �,� �� �*�� �W*� �W� ��G=4�������շ>j�'?Q��=pu�� � changemodel� -/accs/206odin_spear_red/mdl/odin_spear_kr.kmd