	� G !/item/bear_gold/mdl/bear_gold.kmd��       �W[o�6~V�`Ap^�U�)�Nh~H�,���E�{3h��	]�RL���!%˒�\��P�Q����C�tE7��An�ĵsIBj�%f�`�􎏎�X�q!͈K.�.7�����N�k#2���$����RY8��3%w��k����F�
�̄m̉yR#����#C�ͤ7��� 8"\�����d�>�y����|�����!z�q��5,�=��K�x��v�3X8�'�IL�J�m�*?}�ٖ��q�໐�W�:[���!.I���
��3���$Ω"f$ϑ�q�A�[��mj'R?z؆VW(<����[��[��p��kJ2�j}5Q�\�AR5))���d��y:����J-邙�0M��)h�\�2AwlA����CK�^K�_oID�ΰ���~�o� ��/?]Ҕ
����3������w��˲�1��`� f�J�,�c�~��̌���)7F}����V=@�� ;ч婣6X*M�K.�`�d�8���+����W��4�3��w�tFX�@��̞>�y��i��L�G?;��;��o-L���%Q��(C�Ã�����:�2�M�zw]#��9�Z�Q9Q)�7�y\�$_�����3�?���a�_7�6��5��z_�6Ыev0\��zz�J����-
�T��b�{M�#Z�
#����X�IC���i4<�	i[�w.��w�w��S�=�uA�M����Vᨍ8��B��K�à�9]q� �����ӝ���%~�hxZ���jᷞ� �5������n[V�]��c������0( zp������?R�W���=��o46�%,<ӗ��&Ȅ�Ŗ	p��*	�%�����rQ�-ZB��tĠmW���>�X��4���7FA���^�1 �� �������׳�._��j�?��4��hzs���0��n2�������q��l��s�_O   a  Lga2/stage/AmpedScript; e Lkotori/data/MixtureData; mix�� kotori.data.MixtureData� /efx/mix/bar2.agiff� start startattack 
showweapon hit hitstage reflect broken destroy countdown_5 setShadowState
 d s 3 
setVisible (Z)V
 f u v
 H 0 3 roll (Lkotori/geom/Vector3D;I)V
 f y z-Dz           
setMixture (Lkotori/data/MixtureData;)V
 d } ~ mixtureMotion (IFFF)V
 d � ��@   removeMixture
 d � 1 vibe (FFFFI)V
 d � �<�G�P  yquake
 d � 3 	playSound
 d � 3D /efx/electric/lightning01.scm�    >�  =L��?@  ?@  ?@                rndc (F)F
 H � � Lkotori/data/KGLGenerationData; � hpb (FFF)V
 � � �C4   generate A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
 d � � start_pale_yelD /efx/electric/lightning08.scm�    >�  =L��?���?���?���              
start_goldD /efx/explode/shockw1.scm�    >�  =L��?   ?   ?                /(Ljava/lang/Object;)Lkotori/gfx/KGLScriptModel;
 d � �D /efx/flash/glow01_gold.ptc�    >�  =L��?�ff?�ff?�ff             -�           rotate (Lkotori/geom/Quaternion;I)V
 f � ��� kotori.geom.Quaternion?�              -�           changePickable
 f � v (II)V
 d � �-            ��?�              ��  D efx/electric/lightning08.scm�    >�  =L��?   ?   ?               D /efx/flash/glow01_gold.ptc�    >�  =L��?��?��?��             rndf
 H � �C�   explode
 d � 1D /efx/electric/lightning01.scm�            ?@  ?@  ?@               D /efx/electric/lightning08.scm�            ?���?���?���             D /efx/explode/shockw1.scm�            ?   ?   ?               D /efx/flash/glow01_gold.ptc�            ?�ff?�ff?�ff              dropStageItem (IIII)V
 f � � kill
 f � 1 	(IFFFFF)V
 d � �C�  D efx/electric/lightning08.scm�    >�  =L��?   ?   ?               D /efx/flash/glow01_gold.ptc�    >�  =L��?��?��?��             f h g
 - 1  i� �  j 1  *� tW*� wW*d� xW*� wW�  k 1  *� tW*� xW*|� {W�  l 1  %*� xW*� � W*�� �W*N � xW*� �W�  m 1  �*��,� �W*� �W*� �W�L=� $+�� ��� ��� �� �W*+�� �W�W
����N=� $-�� ��� ��� �� �W*-�� �W�W����:*� �W*�� �W�  n 1  '*� xW*{� �W*�� {W*,� xW*�d� �W�  o 1  *� xW*{� �W*�� {W�  p 1  *� W�  q 1 |*� �W* �� �W*�� {W*�� �W*� � W*�� �W<� 7�M�N,�� ��� ��� �� �W*,�� �W*-� �W*d� xW�W���*2� xW*� �W*%� �W*� �W*� �W*� wW�:<� &�� ��� ��� �� �W*�� �W�W
����:<� &�� ��� ��� �� �W*�� �W�W����:*� �W*�� �W<� *d� �W�W���<� *d� �W�W���<� *d� �W�W���<� *d� �W�W���*�� xW*� �W�  r 1  �*� tW* ܶ �W*� xW<� �*� � W*�� �W*¶ �W�M�N,�� ��� ��� �� �W*,�� �W*-� �W*d� xW,�� ��� ��� �� �W*,�� �W*-� �W*d� xW*d� xW*� �W� � * ȶ xW�W��g� o��    >���=��>}p�>�9L>�S�  