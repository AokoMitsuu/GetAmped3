	� G &/special/robot_a_normal/robot_base.sknx�       �U�n�0��w�ڛ0ApȀu��A�~�Nm7��2ĀGg�i�&�a����@��&�1vlH����:A����~����d$I�������G��0&�y��X6�B��1��0�8u|,��9/�uGi������"DR�-��;H�:�1�!'��
��ڵ�S"�����I2�ݪ���6殃{t�s!�	���t��uy���up�ͬkN��r�;�۴���x�O���������g��W���̥c!lTTpDCb��Js���h߭ި���jZrE�`� ���gU,����Es��TE�.)�����<�z$"Kb[�G����	Rd���_��O8q�	5h9�\D:��Ѵ,f�.#GP�����D*O�����#����j�i�8�Ȥ&��Hm�3ɇdj�2P���F0�W�3�j�>;��ݗ
TL��)���t�OX���<���J. �r/�w#im�5���p�����xO�N�P�L����l��h� 64�6	��1��E���s��\U����Y��Ĳ�eJA��*TE��wM͋|P;3!�c�j`}ax9Mȭ=Vs�h1���ڬ�ߚѹ�2�b���$dW$و'�V�T΂�e0�-���U*�gӹ	h���3�C��%d�����H�>i���������̐�uiDE/�P�)k���p���6һ��`���R�EŢoYd�H·1��p�[����.U�!a�n8�l*�J����??o׶p�֬�$: �ψH���ȴs?n/��ֆ~�x��� }�<��ǒ��t�Z�D�� ���&I⊲�z� G���\��<'%�jB��O�ͣ��W��-[~�*�mT&l�_�c�r�   ]  Lga2/stage/AmpedScript; e Lkotori/data/MixtureData; mix�� kotori.data.MixtureData� /efx/mix/bar1.agi���� red����� ore������� start burnup broken jump dbljump walk fall dash land
 H 0 3 anime (F)V
 d x y (FF)V
 d x {B�  =L�� vibe (FFFFI)V
 d  �<�CH   (FFFZ)V
 d x �B�  A�  =��� generate /(Ljava/lang/Object;)Lkotori/gfx/KGLScriptModel;
 d � �D /efx/explode/exp_sphere1.scm��L��?   >L��>���>���>���              	playSound
 d � 3 
setVisible (Z)V
 f � � rotate (Lkotori/geom/Quaternion;)V
 f � ��� kotori.geom.Quaternion�;�.    ?�      =u 	fadeColor
 d � �?��>��� (II)V
 d � �D /efx/explode/exp_sphere1.scm��L��?fff>���>�=q>�=q>�=q              setColor (FFFF)V
 d � � 
setMixture (Lkotori/data/MixtureData;)V
 d � � mixtureMotion (IFFF)V
 d � ���  D /efx/spark/spark01.ptc�    ?       >���>���>���              (IIF)V
 d � �D /efx/explode/exp_sphere1.scm�>L��>���>L��>��>��>��             Ap   yquake
 d � 3 removeMixture
 d � 1D /efx/explode/exp_sphere1.scm��L��?   �L��>���>���>���              explode
 d � 1 kill
 f � 1 finishAllKGL
 f � 1>L��D /efx/smoke/smoke_spikeshoes.ptc�?   =L��    >���>���>���             D /efx/smoke/smoke_spikeshoes.ptc��   =L��    >���>���>���             D /efx/smoke/smoke_spikeshoes.ptc�?   =L��    ?��?��?��             D /efx/smoke/smoke_spikeshoes.ptc��   =L��    ?��?��?��             D /efx/step/landa_long.scm�>���        ?�  ?�  ?�               D /efx/step/landa_long.scm�>���        ?�  ?�  ?�  B�           D /efx/step/landa_long.scm�����        ?�  ?�  ?�               D /efx/step/landa_long.scm�����        ?�  ?�  ?�  B�            f h g j g l g - 1  i� k�m��  n 1  *� wW*� zW�  o 1  T*� wW*}~� |W*��,� �W*d� wW*}��� �W*�\,� �W*�� �W*� �W*d� wW�  p 1 *� �W*� wW*~�~�,� �W*�� �W*��� |W*�� ȶ �W*� �W*�� �W* ȶ wW*� �W*�� ȶ �W*�� �W*�� �W*�� �W*�� �W*�� �W* ȶ wW*� �W*�� ȶ �W*���� �W*2� wW*� �W*� �W*� �W*�� �W*2� wW*!� �W*� �W*� �W* ȶ wW*� �W�  / 1  *� �W�  q 1  *� �W�  r 1  *� �W�  s 1  *� �W�  t 1  *� �W�  u 1  E� ? *�� �W� .*�� �W*�� �W*d� wW*�� �W*�� �W* ȶ wW���*� �W�  v 1  *�� �W*�� �W*�� �W*�� �W�     ?X,O�l�@?So�?X,O?ˆ� � 	collision�1    >���    ?   >���?   