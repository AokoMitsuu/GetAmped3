	� G   �       �WMo�6>����]�A�E�j�:h�Kz	�k�ɴ�E�v�b�v��ǥ谢�n@.�s�xؿ�K}8�7K�a`��^���yJ�t�D��� �ZF"�ό�a���^�#�k�a$b���17|&���F5c���̓1�4ĭ�����	91}�h��I��B���y"�
!�>��Q$�ۡ���Z��\�1�	+D4I��6c�M=kCԞ�\��I=/�7�j��|Ȱ�	A�i95�Т�>����m3�쳐�T2��۹�  �1g�f�&�$b�A���7l�PDc�����a4�$�7-!Z�Z��NO^�'���izr��>�����}��̇@>��uQ|h�3䢵��Qە$�����ģ�� �K�V���\� ��9�2�l��7�����Ys��P��-)�w�	ɘ̞n8���z��M�[��e��w�:����5�e�1v5F����c���+������8��ܼ놣 h�%U��\8��>����?����<�?Q�ea[A�d�rjUl �f��c��eù:����z�|�ξͷ$u*;���;�2i��_�.�sU���Y�g!?s"E�怗|��<��!0e�s7��h{ů�W�j��~�~��}��j�y_��F^%�ѩy���glL���X|6F����'W�^7��K]O��3�˲�6�^$%%	t}�l�_vx;x��U���.M�s
�����"��Ůu}���D<1���;+hL���E~X"���uwY_�����k�����p���P;G	�ޭ�zE*��!��d��}���KfQ���*Z:���/���8�=Og������_Ͼ|~���7�/N����œ����t�s:�e�Ջ��^d(*R�XE���`�co���.�����2t�J�������l۸ʄ%��d�"������Z�\bX�k��y%U�Q�Sұ7ub^&����C��S�:vW_�\��7�����h����ܚ���_	���TG��X��O�\6   t  Lga2/stage/AmpedScript; e electricfan S_x1_orange S_x1 F_x1_orange F_x1 endsp setHitLevel
 f m 3
 H 0 3 move (FF)V
 f p q 	setMotion (Ljava/lang/String;)V
 f s t accs38 anime
 d w qC�  <��� 	playSound
 d { 3 generate /(Ljava/lang/Object;)Lkotori/gfx/KGLScriptModel;
 d } ~D /efx/special/special1.scm            ?�  ?�  ?�                A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
 d } �D $/accs/electricfan_orange/rimocon.scm            ?���?���?���            	 startD /accs/electricfan/rimocon.scm            ?���?���?���            	 avoidAttack
 f � 3 (FFFZ)V
 d w �C�� A�  @@  C�  C��  
changeFace
 f � 3 Lkotori/gfx/KGLScriptModel; � startScript (Ljava/lang/String;)Z
 � � � start2 startAllItemScript
 f � t electricfan_explodeC�  @�  C�� @�   kill
 � � 1 changeSpecial (II)V
 f � �
 H / 1 finishAllKGL
 f � 1 	getVector ()Lkotori/geom/Vector3D;
 f � �  scale (F)Lkotori/geom/Vector3D;
 � � �>��� gmove
 f � q	 � Q 	 � S  jump (F)V
 f � � setG
 f � �=���C�  =L��
 d { �D /efx/special/special1.scm            ?�  ?�  ?�               D $/accs/electricfan_orange/rimocon.scm            ?���?���?���            	D /accs/electricfan/rimocon.scm            ?���?���?���            	 smove (FFF)V
 f � ���  CÀ C�   collisionMask
 f � 3�   �   @�  CĀ C�  
 H 0 1 	isLanding ()Ljava/lang/Object;
 f � �Cڀ D (/accs/electricfan_orange/electricfan.scm            ?���?���?���            D !/accs/electricfan/electricfan.scm            ?���?���?���            D /efx/step/landa_long.scm�    =���    @   ?�  @                C�  A   Cۀ  unsetSpecial
 f � 1 f  g 1  �  h 1  *� W�  i 3 I�C *� nW*� oW*� rW*v� uW*yz� xW*,� |W*�� W*� oW� *��� �M� *��� �M*2� oW*e� �W*K� oW*� rW*y��� �W*2� oW*�� rW*���� �W*2� oW*���� �W*� �W*e� |W*� oW*� rW,� 
,�� �W*�� �W*�� oW*���� �W,� 
,�� �W* �� oW*���� �W*� �W*2� oW,� ,� �W*}� oW*� �W* ȶ oW*� �W*� �W�  j 1  *� W�  k 3 ��� *� nW*� �M,�� �W*� oW*,� �,� �� �W*� �W*�� �W*v� uW*��� xW*,� �W*�� W*2� oW� *��� �N� *��� �N* �� oW*,� �W*��� �W*���� �W*2� oW*���� �W*� �W*e� |W*2� oW*� �W*�b� �W-� 
-�� �W*�� �W*�� oW*Ƕ rW*ȶ �W*� �W*���� �W*� �W-� 
-�� �W* ȶ oW-� -� �W*���� �W* �� oW� *� �W*� ����*�� �W*� rW*�z� xW� *ж W� 
*Ѷ W*��� �W*� oW*���� �W* �� oW*� �W*���� �W* ȶ oW*� �W* ȶ oW*� �W*� �W�  l 1  ,� & *�� �W*� oW*� rW*� �W* ȶ oW*� �W� v��A                        �  