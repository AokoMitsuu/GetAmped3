	� G !/efx/etc/efx_drag/mdl/capsule.kmd��       �U�n�@�!%R��j7�8~�Bdu�4Q��&�	�h߸��k<yT���`��_��@��{J����y�G%��#'��s�q�̓K�J�X�ǒz�o���`�t
�B�R;���TR��s�����?dS9P�@-`SmK[_2���B>'a��V�ᴂ�>�N=�������9�{/�Ϗ�vm���Eǅ*�lD%���P\&�RT!���3WC2B�h1��)u��#1�XI\�"���G���ND���iK��e��U*�yu�����?�}�z���Oן�ci�'P*L=�>9!e�0�^E<	�E��|2%,���1Q!I�|�R� }>�2a}��`ɲ��T���B��0K_E<e�&Ԙ���aK�w�2JvV1��y��B��OR�3@�i���#��OX��>�!�cPFB�2�z����1�I�[�$��^L���bd�<z$kN<�X��7�Nıg%�3�a��0���C�n���[Qn�kY���)c�i����ݾ��"f�������;�r�_H����ɝON6;��G�vQ��2Z�>�L�w�d�_���nY���p����w�ս�rm��1���G(���A��>�k�vg~�8j�V��]��\;�炘%u)㐺�Z"ڭ�a�~���o��   L  Lga2/stage/AmpedScript; e Lkotori/data/MixtureData; mix�� kotori.data.MixtureData� /efx/mix/bar1.agi���� Lkotori/geom/Quaternion; rot�� kotori.geom.Quaternion��m?[��>*u�>�� start 
showweapon hit timeout reflect setShadowState
 d r 3 rndc (F)F
 H t u j getHPBQuaternionD 5(FFFLkotori/geom/Quaternion;)Lkotori/geom/Quaternion;
 w x yC�   rotate (Lkotori/geom/Quaternion;)V
 f | } (FFF)Lkotori/geom/Vector3D;
 H   roll (Lkotori/geom/Vector3D;I)V
 f � ��z  
 H 0 3 
setMixture (Lkotori/data/MixtureData;)V
 d � � mixtureMotion (IFFF)V
 d � ��@   removeMixture
 d � 1 vibe (FFFFI)V
 d � �<��
B�   	playSound
 d � 3 explode
 d � 1 
setVisible (Z)V
 f � � generate /(Ljava/lang/Object;)Lkotori/gfx/KGLScriptModel;
 d � �D "/efx/water/water_sakedaru1_gre.ptc�            ?�  ?�  ?�               D "/efx/water/water_sakedaru2_gre.ptc�            ?333?333?333             D "/efx/water/water_sakedaru2_gre.ptc�Bp  B�      ?   ?   ?                D "/efx/water/water_sakedaru2_gre.ptc�            >���>���>���             D "/efx/water/water_sakedaru2_gre.ptc�    B�  B�  ?333?333?333             D "/efx/water/water_sakedaru2_gre.ptc�´  B�  B�  >���>���>���              kill
 f � 1��   setRenderType
 d � 3 setBlend
 d � 3 setColor (FFFF)V
 d � � 	fadeColor
 d � � f h g k j - 1  	i� l��  m 1  0*� sW{� v{� v{� v�� zW*�� ~W*�� �� �W�  n 1  $*� �W*� � �W*�� �W*d� �W*� �W�  o 1  �*� �W*����,� �W*&� �W*
� �W*� �W*� �W*�� �W*�� �W*����,� �W*
� �W*�� �W*�� �W*� �W*�� �W*�� �W*,� �W*� �W�  p 1  *� W�  q 1  m{� v{� v{� v�� zW*�� ~W*� �W*z� �W*�� �� �W*¶ �W*� �W*� �W*� �W* ȶ �W* ȶ �W*� �W�             =��h=k->]��  