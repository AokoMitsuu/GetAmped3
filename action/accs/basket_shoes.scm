	� G  U�       �X[��D~����싳�z=vnK�E��(}�Ry�&�$k�ؖ��i��6	������VH�$Z�Z��*�[�-l��83��K�k)����93�|�;��_I�%F��ZX���ױH�jk�[�d��e�.�6]���1;�V%9��GCI�m����v1�dvv6⾭�ݮM�:v�k����	�t�	�`l�x^炥\�ی��+�����������:�e��l%�Q�������]�pg�MD�ux���]�d��F��Co��7:�F���=o��7����FW3=Skp�k��4��.���f��r�Ν]p�74�,����$v��No��i�Y8��1lh�eA�B�����X<b/��Al���<s�h�z�8>���]�Q;�
g���(¶$ʹʾU�QF�X'<�\�SM� �[mb�!�ꁭ� �N�MB5������U��tJb9U��
�V��{��
�v�x�� ��b�q�5->5��s��f6���9�{��֙�t�Af���7��}��ѝ'�{�����郣?�}�nx��7���B?1F��n��{�����#op���'�$<����ш������_<;����߰+�z�]o�������˩�\ �'�߽ɤ��H�����9N6 ��^�v��:y�;�[��,(!)���0����b�=�6��Ɔ��\��c�E�t����f�h�NT)eA��[��}l��i���Cm��)�$�g�Qjj��ӧ�An��S����mZ]Cݧ��G�L� �B���\G��Y����, �V�b��3�z�z��ik�^����-���-C?F��d.5.r|�Ocv
%�TS����u����� �L[9�� E-�����h�v�`R>�̀e�^ѻ[%����q���s۾��/�	�P�:
��S��.C��e��]9;���r^A���@�%/	(Ԗ/�'��P^�.	=�2<�)�M:C��wm�.������	���2K�Ƿaw2 �a6��p��Cw�r��((=4����b�S`2�����	F�Z��T�ZU�q1�c^"����_�s�ǒ��\O������0ƕ�1�-
������b@����_J�,$��DRa ��Bh~�=q�7�L���Ld��f�D�&Ey)�B��RsN_�P1�"��D��p�Cf��h]|б�rTwv��K��M���h�jH�4��v�XL-�R,)'l?ij���է�?�����]lџ�'uO�P��l~E4��e*�H�P$��llU�zp�D)d,�Z�iǉ�`ݛ��u�֧�-�/,|���+��^�"�Ų�e�g(JP(BO�rS�iIqٌ�����K�.-�ʡV׻A�H-Yi)�b�AN��q+���s4�UzJ��<t#K�l4a�NM:�ߐ0���(��$��˷�ha��RLj^�L�
y9_P�4���ޛ8/�n���
�-x�/�<���M��6ފ4Iv����T��_��u��!�ZK��^,-f�OM�?A��e�1.��h�f�ui��z՜4���Dx������^^�ʩm��qr��-._b�� S�E~�w�\~�C5s�   �  Lga2/stage/AmpedScript; e Lkotori/data/MixtureData; bash_mix�� kotori.data.MixtureData� /efx/mix/bar2.agi �� cnt F_00 F_b1 
skill_bnd1 skill_bnd1_col F_b1_cmb 	F_b1_land F_x1 
skill_land 
skill_bnd2 skill_bnd2_col setHitLevel
 f u 3
 H 0 3 	setMotion (Ljava/lang/String;)V
 f x y base anime (FF)V
 d | }Bx  =L�� jump (F)V
 f � �A    generate /(Ljava/lang/Object;)Lkotori/gfx/KGLScriptModel;
 d � �D /efx/step/step3.scm�    >����L��?�ff?�ff?�ffC4            setLandStop
 f � 1 finishAllKGL
 f � 1 accs4 smove (FFF)V
 f � �@@  B   avoidAttack
 f � 3��  A@   	playSound
 d � 3 (FFFZ)V
 d | �B  A�   
changeFace
 f � 3 setHitScript
 f � y attack E(Lkotori/geom/Vector3D;Lga2/stage/atk/AttackParam;)Lga2/stage/Attack;
 f � �-    >���?333� d2 � hit_bb  d� ^     
� setWallScript
 f � y setLandScript
 f � yB   	getVector ()Lkotori/geom/Vector3D;
 f � �  	normalize
 � � � (FFF)Lkotori/geom/Vector3D;
 H  � scale (F)Lkotori/geom/Vector3D;
 � � � vx	 f �  vz	 f �  .(Lkotori/geom/Vector3D;)Lkotori/geom/Vector3D;
 � ) � A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
 d � �D /efx/shockwave/shockwave1.scm�    ?   ?   ?�  ?�  ?�      ´       start2 (II)V
 d � �B    gsmove
 f � �	 � Q A`  ��  	 � S  gturn
 f � }D /efx/aura/aura_kick01.scm�    ����?L��@,��@,��@,��C4  �\  B�  -    >L��?333� F2 � hit_b1  d� ^     
�
 d | � vibe (FFFFI)V
 d � ����
�`  D /efx/aura/aura_kick01.scm�    ����?L��@,��@,��@,��C4  �\  B�  -    >L��?333� '2 �  d� ^     1 �B�   move
 f � }@�  B�  =�\)B�  
 H / 1A   =���D /efx/step/smoke_lan5.ptc�        >���?��?��?��C4           D /efx/special/special1.scm            ?�  ?�  ?�                startIllusion
 d � 3 
setMixture (Lkotori/data/MixtureData;)V
 d � �D /efx/aura/aura_kick01.scm�    =���?�  @,��@,��@,��C4  �   B�  D /efx/aura/aura01.scm�=���>���?L��?fff?fff?fffC4  �   B�   	start_blu-    >���?333� F2 �  d� �     
� endIllusion
 d � 1B$  B,  A�  -    ?��?L��� d2 � hit_bc  d� �     c d removeMixture
 d � 1D /efx/shockwave/shockwave1.scm�    ?   ?   ?�  ?�  ?�      ´      D /efx/aura/aura_kick01.scm�    ����?L��@,��@,��@,��C4  �\  B�  D /efx/aura/aura01.scm�=���>���?   ?fff?fff?fffC4  �\  B�  -    >L��?333� O2 �  '� �     
�>L����33D /efx/aura/aura_kick01.scm�    ����?L��@,��@,��@,��C4  �\  B�  -    >L��?333� 2 �  '� �     1 � f h g j  - 1  i� �  k 1  I� C *� vW*� wW*{� zW*�� ~W*�� �W*�� �W* ȶ wW*� �W*ж wW*� �W�  l 1  �� � *� vW*� wW�*�� zW*�� �W*��� ~W* ȶ �W*d� wW*� �W*<� wW*��� �W*d� �W*���� �W*� �W*
� wW*o� �W*��� �W*m� �W*p� �W* ȶ wW*ж wW*� �W�  m 1  �� � *�� zW*n� �W*� wW*� �W*��� ~W*� �� �L*� �*� �� �� �� �M+,� �W*��� �W*� �W*�� �W* �� wW*e� �W*���� �W*+� ��j�+� ��j� �W*+� �+� ȶ �W*˶ �W*d� wW*�Ͷ �W*� �W*� wW*� �W*� �W�  n 1  �*�� zW�`Y�W�� *� vW� 
*n� �W*p� �W*� wW*� �W*¶ �W*� �� �L*� �W*�\ ܶ �W* ȶ wW*+� ��jbj�+� ��jbj� �W*Զ �W*(� wW*�ֶ �W*� �W*�� wW*� �W*� �W�  o 1  q*� wW*{� zW*��,� �W*� �W* �� wW*c� �W*ڶ �W*�ܶ ~W*� �W* ȶ wW*���� �W*� �W*ж wW*� �W�  p 1  B*� wW*{� zW*߶ �W*�� ~W*� �W* �� wW*� �W* ȶ wW*� �W�  q 1  �� � �*� wW*� vW*�� zW*� �W*��� ~W* ȶ �W*,� �W*� �W* ȶ wW*� �W*��� �W*d� �W*� � �W*���� �W*� �W*t� �W*� �W*�� �W*s� �W*� �W*2� wW*�� �W*ж wW*� �W*� �W*� �W�  r 1  �� � *�� zW*� vW*� wW*� � �W*� �W*��� ~W*� �W*2� wW*e� �W*� �W*P� wW*���� �W*� �W*��� �W*�� �W*(� wW*� �W*� �W*� �W*�� wW*� �W*� �W* ȶ wW*� �W*� �W*� �W�  s 1  �� � *�� zW*� vW*� wW*� � �W*� �W*��� ~W*� �� �L*� �*� �� �� �� �M+,� �W*��� �W*e� �W*� �W*� �W*�� �W* �� wW*���� �W*+� ��j�+� ��j� �W*+� �+� ȶ �W*�� �W*�� �W*d� wW*t� �W*��� �W*� �W*� �W*� wW*� �W*� �W*� �W�  t 1  �*�� zW�`Y�W�� *� vW� 
*n� �W*� wW*� �W*¶ �W*� �� �L*� �W*�\ ܶ �W* �� wW*+� ��j�j�+� ��j�j� �W*�� �W*(� wW*��� �W*� �W*(� wW*+� ��jbj�+� ��jbj� �W*�� wW*� �W*� �W*� �W� �b�                        �  