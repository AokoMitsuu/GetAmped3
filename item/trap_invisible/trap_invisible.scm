	� G +/item/trap_invisible/mdl/trap_invisible.kmd��       �ROO�0?x	�a�K1st[D�A�M�!�f
}@��.�CF_ܓ�0t���{�o=z�a�3{�|7C6�2I��c%RlW+ՊHR�Ў$J%\ΐ�Ǧ�9.\늝�ܾ��J��Z�X�E�	���'�#�<�MEM�DrИ���������\tc�.S+�Em�u�k۷�E]}��K��*.���Y!�*�o�)���­X����fnS��"�D�ho������^�P�$p覹.S3D6��@ʲ��K�8&�!�(�K_�3�\K5���E���N�:g`?�i(}��s�|���lf&���C+i�V�\�9�����U�e0A�$�z��ހ�<z_�$�1�Z��i�d�%Z2Tru�n_�N+87��X�9R,{(21���Z�>�.�	������7�   6  Lga2/stage/AmpedScript; e Lkotori/data/MixtureData; mix�� kotori.data.MixtureData� /efx/mix/bar1.agi���� red����� ore������� start startattack 
showweapon hitstage reflect broken destroy setShadowState
 d u 3 
setMixture (Lkotori/data/MixtureData;)V
 d w x mixtureMotion (IFFF)V
 d z {�@  
 H 0 3 roll (Lkotori/geom/Vector3D;I)V
 f  �-Dz           anime (FFFZ)V
 d � �A   A�  Bp   removeMixture
 d � 1 	playSound
 d � 3-�           rotate (Lkotori/geom/Quaternion;I)V
 f � ��� kotori.geom.Quaternion?�              -�           explode
 d � 1 
setVisible (Z)V
 f � � kill
 f � 1 f h g j g l g - 1  i� k�m��  n 1  *� vW*� � yW*}� |W�  o 1  *� ~W*�� �W�  p 1  1*� ~W*���� �W*� � yW*}� |W*N � ~W*� �W�  q 1  '*� ~W*{� �W*�� �W*,� ~W*�d� �W�  r 1  *� ~W*{� �W*�� �W�  s 1  *� W�  t 1  %*� ~W*&� �W*� �W*� �W*d� ~W*� �W� 8"��    <��    >�Z>���>�Z�  