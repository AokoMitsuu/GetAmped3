	� G -/item/frisbee_crystal/mdl/frisbee_crystal.kmd ��       �V�n�6}v���E�VdI�wS�~��A�m.�ڻ���(��$
$}K�o�/uHJ�.P�m�E'<ÙCΜ#���*Z$�I�IE�����4�Ă�*����`yɅBK��`^B�:�)<�<?��CtOB��b^ P(y�����m��#<��Z	:n��-�wv^//Z�nG�M�=���FD��9kC~+�	���ON�|��=��vw;���)~y��,Ar�7JJ^�z%�����`9�}w�����LRK��c`�4Pn׏\1(�����ݫ�kʘ�����9_�:]Co�QM3+{�2#�	_	�n`r�-��D󘾰��_%�9�wO��֌n��@�W��d��#$c�Q�{���E���Ͼ>)���?27�t������d&ԛg|��T�\{�����C�F�7����
���;�����n�t�E�x�g,g
�U�tN+�8QH�AIWؘ�w{<<�_���iL�%޺;���s{c��溨�*lW�)f?]�F������M�L|��o�ْ	�ޗ
��Zo�jY0e<e��R-�T*�Ċ6��k�~�S1ݕ�C�==?|��uZ�o2�c�y�{�>��u�g0��5?��9h��F�eΫ��,�Nt'�4.`�Y�ۀ���z	�A��I��I�M$ᛉ��3#֖_�^0L���w_}�ku[􇅾O�}��\�_Y�����,������U=����6GAs�f�>Ze�fE��Fpb�
�:}/8N�Y����8K�.L����5�8K�@�������D�T�d�A�T�u�|�n|�zD/H���睛^�^����N��;�����z\�{�+X����tL~J�ʵE�ڋ3J�Y�?$`�����j@SQ�f6��͂���_�Vw�{��`l8����RSg�+�eD#�]{p�fJ��ܼ]�x��N��۵
6�+��.O�Vo�F�����7�*ǿ�	   p  Lga2/stage/AmpedScript; e Lkotori/data/MixtureData; mix�� kotori.data.MixtureData� /efx/mix/bar1.agi���� 
showweapon reflect hitstage hit start start2 test p2 (F)F destroy timeout drop2 fall
 H 0 3 anime (FFFZ)V
 d x y@@  @�  A�   
setMixture (Lkotori/data/MixtureData;)V
 d ~  mixtureMotion (IFFF)V
 d � ��@   removeMixture
 d � 1 	playSound (II)V
 d � �D /efx/ice/icecloud.scm�            >�33>�33>�33    B�       D /efx/flash/glow01_frz8.ptc�            ?���?   ?���             Lkotori/scp/KotoriScript; � 
accumlimit
 � � 3 rndc
 H � r>���>L�� Lkotori/data/KGLGenerationData; � pos (FFF)V
 � � � generate A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
 d � � /(Ljava/lang/Object;)Lkotori/gfx/KGLScriptModel;
 d � � start2_kira 	depthTest (Z)V
 d � � setRenderType
 d � 3 setBlend
 d � 3 setColor (FFFF)V
 d � �
 d � 3 explode
 d � 3 kill
 f � 1 println (Ljava/lang/String;)V
 H � � setShadowState
 d � 3 move
 f � ���� roll (Lkotori/geom/Vector3D;I)V
 f � �-    D      D /efx/ice/icecloud.scm�            >�33>�33>�33    B�       D /efx/flash/glow01_frz8.ptc�            ?���?   ?���            ���� sin
 H � r@H��?���<o-             sv ()Lkotori/geom/Vector3D;
 d � �  clear
 � � �	 � Q =#�
	 � R ;-� (Lkotori/geom/Vector3D;)V
 d � � .(Lkotori/geom/Vector3D;)Lkotori/geom/Vector3D;
 � + � groundCheck (Lkotori/geom/Vector3D;)Z
 f � � f h g - 1  i� �  j 1  2*d� wW*{|}� zW*� � �W*�� �W*,� wW*� �W�  k 1  |*� �W�L�M>� h* ȶ �W�� �8�� �8�� �8+� �W*+o� �Wp� 	*,� �W*(� wW+� �W*+�� �W*� wW�W����  l 1  ;*� �W*� wW*� �W*� �W*� �W*� �W*
� wW*� �W*� �W�  m 1  *m� �W�  n 1  �*� �W*�� �W*�� �W�L�M>� n* ȶ �W�� �8�� �8�� �8+� �W*+o� �Wp� 	*,� �W*(� wWp� +� �W*+�� �W*<� wW�W����  o 1  E*�� �W*� �WDEF� '*#�j� �� *#�j� �� jE*
� wW#�bD���*� �W�  p 1  *ж wW*� �W�  q r  ##j�  s 1  �  t 1  *� 	W�  u 1  *� �W*� W*� �W�  v 1  k*d� wW�L*� �M,� �W>,ʵ �*2� wW� :,Y� ��f� �*,� �W+,� �W*+� ә �W,,� �vbj� �*� wW���*X� wW� ���-            >��=vJ�>��o�  