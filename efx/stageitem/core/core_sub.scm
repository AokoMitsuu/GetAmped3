	� G /efx/stageitem/core_sub1.mot��       �V[kG~���àPXٛ�H�$�zPl׵��vBߖ�vV�7vG��	t�>�m�Rp�Z($!�C�`��?�N������YY��M)�,��̙3�3߹|vB���v�Z�����V�Bb�6#������� b� `AD53��U��[$�򦝮��O�vY'"���h��n��(��ٙ���Pc㛽�;k���͵�=P/0ҭu�tu���q���-rѾ�A��;�aVѺ|ݑ��x��<ʀl�ߕ�k�9�c1U�+�a@m������D�1�<P*������v�p�aF�rIh�O=��rM��$�Rtc}�k�ȱ�4YD�z��kw|�DZ�􊥣0�C54Su��at��f���wHc
g����C���b�ٷ�AD��jA����t�*��L?��i��tp<;�8Vs����!%3�:.��z���Z1�0m�� YEC��"�p?Y-!3r�6��W7���g
���PVQ�?S��4��*�/-�I�ٌ�
�ELyr�|����j�������o���W�PT2T�P�u�pv��go?�����~H��i�K�O�N�||�9����-��l������'حm�_]_�V�:���ؓNl�{���i���u�P�*���&p�_�*��ߪ�뜔�ςy�	�Hd�W\��v�7�7���o�i��6�WoS�y��7 ^��cߔr,]�a���������x �o.p掑W���Q��iT�t�37*r�TFA
a+���&V��n�X_�W�Tc��"<O�LRp�QE}���5?/���9�BD����=Oyј�|0_�8��ʒ��v� 3�JL��+JY�����04y?V���5��F&��h�jF)Vr�ᨨ�rMGԂ����Y�ӻ��F��t�"�9��$M�@y���q������I��ɫ4�.M^N���c���|�X���� !D�5p�������]���9ti6ޱ�\oSY������@�Td�?���˂e�	`mN?��M����� m>�eUq��LG|�PD@�1I7t���p0�^aD�L��f���칔��d�LV�K�S^�T���\�b��BL�y\��l<�*F��U�F8_ PF�6�V|�����̊��tN-�X�A���ɼ��_G?u1-9x��?��6M~�����u�}�vUc-򔜧�)��B����   d  Lga2/stage/AmpedScript; e Lkotori/data/MixtureData; mix�� kotori.data.MixtureData� /efx/mix/bar7.agi���� Lkotori/gfx/KGLScriptModel; mix1 mix2 start ugoki destroy exp end avoidAttack
 f r 3 setShadowState
 d t 3 anime (F)V
 d v wA    generate A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
 d z {D efx/electric/rnd_thunder.scm�    =���    ?�  ?�  ?�      B�      A sub_core
 H 0 3D /efx/flash/hikari_straight.scm�    =L��    ?�  ?�  ?�               	start_greD /efx/flash/hikari_straight.scm�    =L��    ?�  ?�  ?�               setColor (FFFF)V
 d � �=��� 	fadeColor (FFFFI)V
 d � �>��� (FF)V
 d v �AP  =L��A�   
setMixture (Lkotori/data/MixtureData;)V
 d � � mixtureMotion (IFFF)V
 d � �Ap  A�   (FFFZ)V
 d v �B   BH  D *efx/stageitem/core/core_efx_center_mix.scm�    @   >B�\?�  ?�  ?�               greenD *efx/stageitem/core/core_efx_center_mix.scm�    @   >B�\?�  ?�  ?�      C4       (IFFFFFFF)V
 d � �C�  @�   removeMixture
 d � 1  �P cos (F)F
 H � �<#�
 Lkotori/geom/KMath; � fPI2	 � � E��  move (FFF)V
 f � � 
setVisible (Z)V
 f � � enableCollision
 f � � explode
 d � 1 /(Ljava/lang/Object;)Lkotori/gfx/KGLScriptModel;
 d z �D /efx/explode/exp_cylinder1.scm�            >#�
>#�
>#�
             	playSound
 d � 3 kill
 f � 1<� 	finishKGL (Lkotori/gfx/KGLScriptModel;)V
 f � �D efx/electric/rnd_thunder.scm�    =���    ?�  ?�  ?�      B�      A gattai3_subD /efx/flash/hikari_straight.scm�    =L��    ?�  ?�  ?�              D /efx/flash/hikari_straight.scm�    =L��    ?�  ?�  ?�               f h g k j l j V  - 1  i� �  m 1  �*|� sW*� uW*y� xW*}~� |W*¶ W*��� |W*2� W*��� |W*���� �W*���,� �W*2� W*��� �W*2� W*��� �W*� � �W*�� �W*2� W*��� �W*2� W*� W�  , 1  �*��� �W*��� |�*��� |�*� W*� W*� W*�� �W*� W*� W*d� �W*� W*� W*�� �W*�� �W<� *� W�W���*� �W<� *� W2`<����*� W�  n 1  .�� ���nj� �jD*#� �W��b���r�*2� W�  o 1  *� W*� �W*� �W*� �W*� W�  p 1  "*� W*�� �W*!� �W*@� W*� �W�  q 1  �*��� �W*� � �W*�� �W*� �W*�� �W*�� �W*��� �� �W*�Ŷ |W* �� W*��� �W*��� |W*2� W*� �W*��� �W*��� |W*2� W*��� �W*2� W*y�� �W*� W*� �W� '�h    @�J    ?fI�?Me�?M�0� � coreType�@   � HP�A   � 	collision�	1    @�    ?L��?333?333