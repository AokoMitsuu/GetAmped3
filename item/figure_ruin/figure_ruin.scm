	� G %/item/figure_ruin/mdl/figure_ruin.kmdp�       �V�o�0��E_�J�F��x�C��QAW���	I%n����wvH��u���L�9���ο;��]81r�eĂ��8�C�L툅�_��k�y$В1Ý����gR���x獈e��CQb����O��y&W�k�� �5���Ho���4A׃F���m�hߓ�� .k��������'�������Vn�{�=�X�el��H->��Y�8��)ub�xԈ�бa��_v6)�z�3�H$�@y� _Af��V���X� �>'^L�0$q���D���7E�Db/��P�g�<��N:�-x�-\r��%!���)xN+�CY��Oޯ�`�3��ˣN�RYZ&L}�H3Ř&G�C�<M�C���)�?:.�W6�,W�LƷz���r1�s����w��#��I�4��"�O��R�b/H��f/I�ĵBt��-3g��ę�k�4�;#��z�#�p�O@wvx�pk_GW#ЈH��<�.D��d��� E���j����,q��vNӈm��G!�����>R��$_d�T�s=����s�ђV�{$^��6��
��P�m�<�Ex�G�3�;�PҊ����&Ш��B���Qo������|�r�[&�e�,؉:'J@ס�L�;A�V6��WqVfzVY�d�`J�8Pq��U��9�ȠY]m��%�,�J��F-�4 �'�5�r����f�+e��:�\RV1��[r�msڑD���0;�nVLF�����;��z)��#�4n!�#�o!�ɼd�+LS2��^2_ۉ���6�Y�5̺/\���X��^1�*��&ai�||5��>�^[8}�kF�?��T�� �h��h���&|P���X�������X   T  Lga2/stage/AmpedScript; e Lkotori/data/MixtureData; mix�� kotori.data.MixtureData� /efx/mix/bar7.agi�ff� start startattack 
showweapon hitstage reflect broken destroy countdown_5 setShadowState
 d r 3 
setVisible (Z)V
 f t u
 H 0 3 roll (Lkotori/geom/Vector3D;I)V
 f x y-Dz           
setMixture (Lkotori/data/MixtureData;)V
 d | } mixtureMotion (IFFF)V
 d  ��@   removeMixture
 d � 1 	playSound
 d � 3-�           rotate (Lkotori/geom/Quaternion;I)V
 f � ��� kotori.geom.Quaternion?�              -�           changePickable
 f � u (II)V
 d � �-            ��?�              ��  D efx/electric/lightning08.scm�        <���>�  >�  >�              D /efx/flash/glow01_gold.ptc�        <���>�33>�33>�33             rndf (F)F
 H � � Lkotori/data/KGLGenerationData; � hpb (FFF)V
 � � �C�   generate A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
 d � � 
start_gold /(Ljava/lang/Object;)Lkotori/gfx/KGLScriptModel;
 d � � explode
 d � 1D /efx/electric/lightning01.scm�            ?   ?   ?                 rndc
 H � �C4   start_pale_yelD /efx/electric/lightning08.scm�            ?fff?fff?fff             D /efx/flash/glow01_gold.ptc�            ?�  ?�  ?�                dropStageItem (IIII)V
 f � � kill
 f � 1 	(IFFFFF)V
 d  �C�  D efx/electric/lightning08.scm�        <���>�  >�  >�              D /efx/flash/glow01_gold.ptc�        <���>�33>�33>�33             f h g	 - 1  i� �  j 1  *� sW*� vW*d� wW*� vW�  k 1  *� sW*� wW*{� zW�  l 1  %*� wW*� � ~W*�� �W*N � wW*� �W�  m 1  '*� wW*{� �W*�� zW*,� wW*�d� �W�  n 1  *� wW*{� �W*�� zW�  o 1  *� W�  p 1 9*� �W* �� �W*�� zW*�� �W*� � ~W*�� �W<� 7�M�N,�� ��� ��� �� �W*,�� �W*-� �W*d� wW�W���*2� wW*� �W*%� �W*� �W*� vW�:<� &�� ��� ��� �� �W*�� �W�W
����:<� &�� ��� ��� �� �W*�� �W�W���*�� �W<� *d� �W�W���<� *d� �W�W���*�� wW*� �W�  q 1  �*� sW* ܶ �W*� wW<� �*� � ~W*�� �W*�� �W�M�N,�� ��� ��� �� �W*,�� �W*-� �W*d� wW,�� ��� ��� �� �W*,�� �W*-� �W*d� wW*d� wW*� �W� � * ȶ wW�W��g� �*��7�X=dL<b�H>L��>^��>>��  