	� G %/item/maneki_gold/mdl/maneki_gold.kmd��       �W[o�0~N����%��:�:�B�6&.��:o������C⭝P�#?�c'M�4�	�L��=>���;'ޓ�t-h��#��
�Q�8��;u��j�X�D ��0�[�������,�y#�o�w� J���"�%��Siw���ڭ�7	=�5���H�~k�4A��N�.�}���I�� �-�.���_�?]��&�ߝ�\ˍy �aǒ?�`��������F���cX�R'uH@��
f/�tag�g�n�r�"�>Y^�<]�� ���յ3�g�2��/H�R)�I��&�u�ܨ�M�;�8��1���Y@?�,����=\s��%1����)x�+�CY�(�~_p�@�r2���KeiA�<0�-�:��\2�8R��n�o"W7�A%��G����`�+�W�k=W�KY�����i�e��ԓu�z��zC|�����Y�����4�	��%�M��� �2�ʩHꈄ9��yK��æ�:a�F�J@�a�F�(�l�%�E1�X�Dgcl#�ʔ�Ϟ)�j,㹞D���#@ܰRIk^�/եQu�f1$0��AG)m~�n�`t�����J��*���`�<�;<�`��?[ǕY�̈��]e'Pc��d��y)E��g�*n(�" ��|�M������ą*���5���y�'ܧY�k.ME��jZH���$�G/����ҪMb�^�'����o:k�N;��>9,Q���Y�F�_к`���M�h'O[�us�G�{"����-��GY��W��)נ��9%�"��f�'�&g�pR�؉/� o3}�6=g�����V��bn�cu=}+h�#8\�C����L���uӼ�����b��܁NpE�fß\L+��z�|��m�����ɇ������Y���L{�q��������il��pw��/l��i�Y\��   a  Lga2/stage/AmpedScript; e Lkotori/data/MixtureData; mix�� kotori.data.MixtureData� /efx/mix/bar7.agiff� start startattack 
showweapon hitstage hit reflect broken destroy countdown_5 setShadowState
 d s 3 
setVisible (Z)V
 f u v
 H 0 3 roll (Lkotori/geom/Vector3D;I)V
 f y z-Dz           
setMixture (Lkotori/data/MixtureData;)V
 d } ~ mixtureMotion (IFFF)V
 d � ��@   removeMixture
 d � 1 	playSound
 d � 3-�           rotate (Lkotori/geom/Quaternion;I)V
 f � ��� kotori.geom.Quaternion?�               vibe (FFFFI)V
 d � �<�G�P  yquake
 d � 3D /efx/electric/lightning01.scm�            ?   ?   ?                 rndc (F)F
 H � � Lkotori/data/KGLGenerationData; � hpb (FFF)V
 � � �C4   generate A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
 d � � start_pale_yelD /efx/electric/lightning08.scm�            ?fff?fff?fff              
start_goldD /efx/explode/shockw1.scm�            >�33>�33>�33             /(Ljava/lang/Object;)Lkotori/gfx/KGLScriptModel;
 d � �D /efx/flash/glow01_gold.ptc�            ?�  ?�  ?�               -�           changePickable
 f � v (II)V
 d � �-            ��?�              ��  D efx/electric/lightning08.scm�        =L��>�33>�33>�33            D /efx/flash/glow01_gold.ptc�        =L��>�ff>�ff>�ff             rndf
 H � �C�   explode
 d � 1D /efx/electric/lightning01.scm�            ?   ?   ?                D /efx/electric/lightning08.scm�            ?fff?fff?fff             D /efx/explode/shockw1.scm�            >�33>�33>�33            D /efx/flash/glow01_gold.ptc�            ?�  ?�  ?�                dropStageItem (IIII)V
 f � � kill
 f � 1 	(IFFFFF)V
 d � �C�  D efx/electric/lightning08.scm�        =L��>�33>�33>�33            D /efx/flash/glow01_gold.ptc�        =L��>�ff>�ff>�ff             f h g
 - 1  i� �  j 1  *� tW*� wW*d� xW*� wW�  k 1  *� tW*� xW*|� {W�  l 1  %*� xW*� � W*�� �W*N � xW*� �W�  m 1  '*� xW*{� �W*�� {W*,� xW*�d� �W�  n 1  �*��,� �W*� �W*� �W�L=� $+�� ��� ��� �� �W*+�� �W�W
����N=� $-�� ��� ��� �� �W*-�� �W�W����:*� �W*�� �W�  o 1  *� xW*{� �W*�� {W�  p 1  *� W�  q 1 c*� �W* �� �W*�� {W*�� �W*� � W*�� �W<� 7�M�N,�� ��� ��� �� �W*,�� �W*-� �W*d� xW�W���*2� xW*� �W*%� �W*� �W*� �W*� wW�:<� &�� ��� ��� �� �W*�� �W�W
����:<� &�� ��� ��� �� �W*�� �W�W����:*� �W*�� �W<� *d� �W�W���<� *d� �W�W���<� *d� �W�W���*�� xW*� �W�  r 1  �*� tW* ܶ �W*� xW<� �*� � W*�� �W*¶ �W�M�N,�� ��� ��� �� �W*,�� �W*-� �W*d� xW,�� ��� ��� �� �W*,�� �W*-� �W*d� xW*d� xW*� �W� � * ȶ xW�W��g� o�%P����=Y�
=O��>EL>��a>!�q�  